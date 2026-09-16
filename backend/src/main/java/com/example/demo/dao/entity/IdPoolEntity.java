package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId(value = "user_id")
    private Integer userId;

    // 是否使用
    @NotNull
    @TableField(value = "is_used", fill = FieldFill.INSERT)
    private Boolean used = false;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createdAt;
}

