package com.example.demo.validation.annotation;

import com.example.demo.validation.validator.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {

    String message() default "两次密码不一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String confirmPassword();
}
