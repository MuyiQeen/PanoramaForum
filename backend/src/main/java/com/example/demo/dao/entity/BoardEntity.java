package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("board")
public class BoardEntity {
    @TableId(value = "board_id", type = IdType.AUTO)
    private Integer boardId;

    @NotBlank(message = "board's name is null")
    @TableField("board_name")
    private String boardName;

    @NotBlank(message = "board's create_id is null")
    @TableField("creator_id")
    private Integer creatorId;

    //详情页
    @TableField("description")
    private String description;

    @TableField("post_count")
    private Integer postCount;

    @TableField("view_count")
    private Long viewCount;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField(value = "test")
    private String test;
}
