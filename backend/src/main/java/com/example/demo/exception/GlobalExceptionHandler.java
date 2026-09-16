package com.example.demo.exception;

import com.example.demo.constant.WebConstants;
import com.example.demo.dto.response.Result;
import com.example.demo.enums.ErrorEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() == null ? "参数错误" : fe.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));

        log.warn("参数校验失败: {}", errors);
        return Result.error(ErrorEnum.PARAM_INVALID.getErrorCode(), ErrorEnum.PARAM_INVALID.getMessage(),errors);
    }


    /**
     * 处理应用异常（业务异常、认证失败、权限不足等受控异常）。
     * 将异常携带的 HTTP 状态码暂存到 request 属性，
     * 再由 ResultResponseAdvice 在响应写回前读取并设置到真实 HTTP 响应上。
     *
     * @param e 应用异常，携带错误码、HTTP 状态码、错误类型
     * @param request 当前 HTTP 请求，用于暂存 HTTP 状态码
     * @return 统一封装的 Result 对象
     */
    @ExceptionHandler(value = AppException.class)
    public Result<Void> handleAppException(AppException e, HttpServletRequest request) {

        log.warn("应用异常: type={}, http={}, code={}, msg={}", e.getErrorType(), e.getHttpStatus(), e.getErrorCode(), e.getMessage());

        request.setAttribute(WebConstants.HTTP_STATUS_ATTR, e.getHttpStatus());

        return Result.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理资源不存在的情况
     * 统一返回404+资源不存在
     *
     * @param request HTTP请求
     * @return 统一封装的 Result 对象
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public Result<Void> handleNoResource(HttpServletRequest request) {

        String path = request.getRequestURI();

        // favicon 与 devtools 请求是浏览器自动发的，过滤掉避免日志刷屏
        if (!path.startsWith("/.well-known") && !"/favicon.ico".equals(path)) {
            log.warn("资源不存在: {}", path);
        }

        request.setAttribute(WebConstants.HTTP_STATUS_ATTR, ErrorEnum.NOT_FOUND.getHttpStatus());
        return Result.error(ErrorEnum.NOT_FOUND.getErrorCode(), ErrorEnum.NOT_FOUND.getMessage());
    }


    /**
     * 兜底处理所有未捕获的异常。
     * 统一返回 500 + "系统错误"，避免异常细节暴露给前端。
     *
     * @param e 系统级异常（如空指针、数据库异常、第三方调用失败等）
     * @param request 当前 HTTP 请求，用于暂存 HTTP 状态码
     * @return 统一封装的 Result 对象
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Void> handleException(Exception e, HttpServletRequest request) {

        log.error("系统异常", e);

        request.setAttribute(WebConstants.HTTP_STATUS_ATTR, ErrorEnum.SYSTEM_ERROR.getHttpStatus());

        return Result.error(ErrorEnum.SYSTEM_ERROR.getErrorCode(), ErrorEnum.SYSTEM_ERROR.getMessage());
    }


}
