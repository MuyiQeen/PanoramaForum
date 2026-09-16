package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("post")
public class PostEntity {
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    //板块id
    @TableField(value = "board_id")
    private Integer boardId;

    //标题
    @TableField(value = "post_title")
    private String postTitle;

    //内容
    @TableField(value = "post_content")
    private String postContent;

    //发帖人id
    @TableField(value = "user_id")
    private Integer userId;

    //浏览量
    @TableField(value = "view_counts")
    private Long viewCounts = 0L;

    //回复总量
    @TableField(value = "reply_counts")
    private Integer replyCounts =0;

    //点赞
    @TableField(value = "like_counts")
    private Integer likeCounts =0;

    //点踩
    @TableField(value = "dislike_counts")
    private Integer dislikeCounts=0;

    //收藏
    @TableField(value = "favorite_counts")
    private Integer favoriteCounts=0;

    //转发
    @TableField(value = "share_counts")
    private Integer shareCounts=0;

    //置顶
    @TableField(value = "is_pinned")
    private Boolean isPinned = false;

    //精华
    @TableField(value = "is_featured")
    private Boolean is_featured = false;

    //tag
    @TableField(value = "tag")
    private String tag;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
