package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("id_pool")
@Builder
public class IdPoolEntity {

    @TableId(type = IdType.AUTO)
    private Integer seq;

    // id
    @TableField(value = "user_id")
    private Integer userId;

    // 是否使用
    @Builder.Default
    @TableField(value = "is_used")
    private Boolean used = false;

    //创建时间
    @TableField(value = "created_at")
    private LocalDateTime createdAt;
}

