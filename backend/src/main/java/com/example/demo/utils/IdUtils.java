package com.example.demo.utils;

import com.example.demo.dao.entity.IdPoolEntity;

import java.time.LocalDateTime;
import java.util.*;

public final class IdUtils {

    // 防止被实例化
    private IdUtils() {}

    private static final int START_ID = 100000;
    private static final int BATCH_SIZE = 10000;


    public static List<IdPoolEntity> getIdPoolList(int maxId){

        // 设置创建的起始id为100000
        int startId = START_ID;

        // 如果数据库中已有数据则设置为最大的id值
        if (maxId != 0){
            startId = maxId;
        }

        // 用于存储id
        List<IdPoolEntity> idPoolList = new ArrayList<>();

        // 生成100个id 并生成id池对象 然后插入List中
        for (int i = 1; i <= BATCH_SIZE; i ++){

            startId ++;

            // 生成对象
            IdPoolEntity idPoolEntity = IdPoolEntity
                    .builder()
                    .userId(startId)
                    .used(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            // 插入List
            idPoolList.add(idPoolEntity);
        }

        // 打乱id
        Collections.shuffle(idPoolList);

        return idPoolList;

    }




}
