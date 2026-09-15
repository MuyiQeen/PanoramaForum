package com.example.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;
@Component
@Slf4j
public final class FileUtils {
    private final ObjectMapper objectMapper;

    public FileUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean is_exists(File file){
        if (!file.exists()) {
            LocalDateTime now = LocalDateTime.now();
            log.error("{}：文件不存在{}", file, now);
            return false;
        }
        return true;
    }

    /**
     * writeJson 写入json文件
     * @param filepath 文件路径
     * @param map Map数据
     */
    public void writeJson(String filepath, Map<String, Object> map){
        File file = new File(filepath);
        if (!is_exists(file))return;
        try {
            objectMapper.writeValue(file, map);
            LocalDateTime now = LocalDateTime.now();
            log.info("{}：数据写入成功，文件路径{}\n", now, filepath);
        }catch (Exception e){
            System.out.println("文件写入失败："+e.getMessage());
            LocalDateTime now = LocalDateTime.now();
            log.error("{}：数据写入失败\n", now);
        }
    }

    public int readJson(String filepath){
        File file = new File(filepath);
        if (!is_exists(file))return 0;
        try{
            int jsonData = objectMapper.readValue(file, int.class);
            System.out.println(jsonData);
            return jsonData;
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }

}
