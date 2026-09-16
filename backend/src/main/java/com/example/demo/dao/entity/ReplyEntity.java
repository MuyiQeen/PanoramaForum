package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("reply")
public class ReplyEntity {
    //id
    @TableId(value = "replay_id", type = IdType.AUTO)
    private Integer replayId;

    //所属帖子
    @TableField(value = "post_id")
    private Integer postId;
    
    //父回复id
    @TableField(value = "parent_id")
    private Integer parentId;

    //被回复id
    @TableField(value = "reply_to_id")
    private Integer replyToId;

    //回复者id
    @TableField(value = "reporter_id")
    private Integer reporterId;

    //回复内容
    @TableField(value = "reply_content")
    private String replyContent;

    //回复总数
    @TableField(value = "reply_counts")
    private Integer replyCounts = 0;

    //点赞
    @TableField(value = "like_counts")
    private Integer likeCounts =0;

    //点踩
    @TableField(value = "dislike_counts")
    private Integer dislikeCounts=0;

    //神评
    @TableField(value = "is_hoted")
    private Boolean isHoted =false;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //软删除
    @TableLogic
    @TableField("deleted")
    private Boolean deleted = false;
}
