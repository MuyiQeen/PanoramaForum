package com.example.demo.dto.request;

import com.example.demo.validation.annotation.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class RegisterRequest {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;


    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 32, message = "密码长度必须在 8-32 位之间")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 8, max = 32, message = "密码长度必须在 8-32 位之间")
    private String confirmPassword;

    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "\\d{6}", message = "验证码必须是 6 位数字")
    private String validateCode;
}
