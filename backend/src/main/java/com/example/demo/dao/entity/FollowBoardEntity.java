package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.enums.FollowStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "follow_board")
public class FollowBoardEntity {
    //
    @NotNull
    @TableId(value = "user_id")
    private Integer userId;

    //
    @NotNull
    @TableField(value = "board_id")
    private Integer boardId;

    //
    @NotNull
    @TableField(value = "status")
    private Integer status;

    //创建时间
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
