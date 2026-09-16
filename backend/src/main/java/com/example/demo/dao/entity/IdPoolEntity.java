package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("id_pool")
@Builder
public class IdPoolEntity {
    // id
    @NotBlank
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    // 是否使用
    @NotNull
    @TableField(value = "is_used")
    private Boolean used = false;

    //创建时间
    @TableField(value = "created_at")
    private LocalDateTime createdAt;
}

