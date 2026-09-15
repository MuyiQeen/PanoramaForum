package com.example.demo.service.impl;

import com.example.demo.dao.entity.IPPoolEntity;
import com.example.demo.dao.mapper.IPPoolMapper;
import com.example.demo.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class IPPoolImpl {
    private final IPPoolMapper ipPoolMapper;
    private final FileUtils fileUtils;

    private void generateIP(int generateCount){
        String filepath = "src/main/resources/json/IPPool.json";
        ArrayList<Integer> ipList = new ArrayList<>();
        int json = fileUtils.readJson(filepath);
        int max_ip = json;
        if (json != 0){
            for(int i=max_ip; i<generateCount + max_ip+1; i++){
                ipList.add(i);
            }
        }else {
            max_ip = 100000;
            for (int i =max_ip; i < max_ip+generateCount; max_ip++){
                ipList.add(i);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("maxIP",ipList.get(ipList.size() -1));
        fileUtils.writeJson(filepath, map);
        for (int i = 0; i < ipList.size()-1; i++) {
            IPPoolEntity a = new IPPoolEntity();
            a.setUserId(i);
            ipPoolMapper.insert(a);
        }
    }
}
