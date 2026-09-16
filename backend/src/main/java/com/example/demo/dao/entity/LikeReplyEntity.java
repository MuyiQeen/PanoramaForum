package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("like_reply")
public class LikeReplyEntity {
    //userid
    @TableId(value = "user_id")
    private Integer userId;

    //回复id
    @TableField(value = "reply_id")
    private Integer replyId;

    //状态
    @TableField(value = "status")
    private Integer status = 0;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
