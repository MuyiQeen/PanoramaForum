package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("ip_pool")
public class IPPoolEntity {
    //
    @NotBlank
    @TableField(value = "user_id")
    private Integer userId;

    //
    @NotNull
    @TableField(value = "is_used")
    private Boolean isUsed = false;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}

