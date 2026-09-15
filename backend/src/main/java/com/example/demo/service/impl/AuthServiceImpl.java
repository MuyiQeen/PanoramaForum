package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.entity.IdPoolEntity;
import com.example.demo.dao.mapper.IdPoolMapper;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.Result;
import com.example.demo.service.AuthService;
import com.example.demo.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends ServiceImpl<IdPoolMapper,IdPoolEntity> implements AuthService{

    private final IdPoolMapper idPoolMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateId() throws Exception {

        QueryWrapper<IdPoolEntity> wrapper = new QueryWrapper<>();
        wrapper.select("IFNULL(MAX(user_id), 0)");

        try {
            Object maxValue = idPoolMapper.selectObjs(wrapper).get(0);

            List<IdPoolEntity>  idPoolList =IdUtils.getIdPoolList(Integer.parseInt(maxValue.toString()));

            saveBatch(idPoolList, 100);
        } catch (Exception e) {
            throw new Exception("生成 ID 池失败", e);
        }
    }

    @Override
    public Result<Void> register(RegisterRequest request) {

        return null;
    }


}
