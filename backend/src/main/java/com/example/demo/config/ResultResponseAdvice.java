package com.example.demo.config;

import com.example.demo.constant.WebConstants;
import com.example.demo.dto.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Class<?> type = returnType.getParameterType();
        return Result.class.isAssignableFrom(type);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (request instanceof ServletServerHttpRequest
                && response instanceof ServletServerHttpResponse) {

            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

            Object status = req.getAttribute(WebConstants.HTTP_STATUS_ATTR);
            if (status instanceof Integer) {
                resp.setStatus((Integer) status);
            }
        }
        return body;
    }
}