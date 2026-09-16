package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.entity.IdPoolEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.mapper.IdPoolMapper;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.Result;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.AppException;
import com.example.demo.service.AuthService;
import com.example.demo.utils.BCryptUtils;
import com.example.demo.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl extends ServiceImpl<IdPoolMapper, IdPoolEntity> implements AuthService {

    private final IdPoolMapper idPoolMapper;
    private final UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateId() throws Exception {

        QueryWrapper<IdPoolEntity> wrapper = new QueryWrapper<>();
        wrapper.select("IFNULL(MAX(user_id), 0)");

        try {
            Object maxValue = idPoolMapper.selectObjs(wrapper).get(0);

            List<IdPoolEntity> idPoolList = IdUtils.getIdPoolList(Integer.parseInt(maxValue.toString()));

            saveBatch(idPoolList, 100);
        } catch (Exception e) {
            log.error("");
            throw new Exception("生成 ID 池失败", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> register(RegisterRequest request) {

        UserEntity user = userMapper.selectByEmail(request.getEmail());

        if (user != null) {
            throw new AppException(ErrorEnum.USER_EXIST, "该邮箱已被注册！");
        }

        int id = idPoolMapper.selectNextUnused();
        user = UserEntity.builder()
                .userId(id)
                .email(request.getEmail())
                .passwd(BCryptUtils.hashPassword(request.getPassword()))
                .build();

        UpdateWrapper<IdPoolEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",id)
                .set("is_used",true);


        try {
            userMapper.insert(user);
            idPoolMapper.update(null,updateWrapper);
            log.info("用户插入成功{}", user.getUserId());
        } catch (Exception e) {
            log.error("用户插入失败{}", e.getMessage(), e);
            throw new AppException(ErrorEnum.SYSTEM_ERROR, "用户注册失败");
        }

        return Result.ok("用户注册成功");
    }


}
