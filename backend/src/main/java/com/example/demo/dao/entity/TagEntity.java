package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tag")
public class TagEntity {
    //id
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;

    //name
    @NotBlank
    @TableField(value = "tag_name")
    private String tagName;

    //背景颜色
    @TableField(value = "tag_bgc")
    private String tagBgc = "#808080";

    //文本颜色
    @TableField(value = "tag_color")
    private String tagColor = "#000000";

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
