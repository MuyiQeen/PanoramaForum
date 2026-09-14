package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class UserEntity {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField("username")
    private String username;

    @Email
    @NotBlank(message = "email is null")
    @TableField("email")
    private String email;

    @NotBlank(message = "password is null")
    @TableField("passwd")
    private String passwd;

    @TableField("avatar_path")
    private String avatarPath;

    @TableField("role")
    private Integer role=12;

    //软删除
    @TableLogic
    @TableField("deleted")
    private Boolean deleted = false;

    //禁用
    @TableField("disable")
    private Boolean disabled = false;

    //创建时间
    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    //更新时间
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    //积分
    @TableField("points")
    private Long points = 0L;

    //性别
    @TableField("gender")
    private Integer gender = 2;

    //关注
    @TableField("following_count")
    private Integer followingCount = 0;

    //粉丝
    @TableField("follower_count")
    private Integer followerCount = 0;
}
