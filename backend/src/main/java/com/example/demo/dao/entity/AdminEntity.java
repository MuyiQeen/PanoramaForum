package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_account")
public class AdminEntity {
    private String email;
    private String password;
}
