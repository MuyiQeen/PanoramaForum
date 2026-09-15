package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "board's create_id is null")
    @TableField("creator_id")
    private Integer creatorId;

    //详情
    @TableField("description")
    private String description;

    //总贴数
    @TableField("post_count")
    private Integer postCount = 0;

    //浏览量
    @TableField("view_count")
    private Long viewCount = 0L;

    //权重
    @TableField("sort_order")
    private Integer sortOrder = 0;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}
