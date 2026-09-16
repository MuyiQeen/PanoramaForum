package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("like_post")
public class LikePostEntity {
    //
    @TableId(value = "user_id")
    private Integer userId;

    //
    @TableField(value = "post_id")
    private Integer postId;

    //
    @TableField(value = "status")
    private Integer status;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
