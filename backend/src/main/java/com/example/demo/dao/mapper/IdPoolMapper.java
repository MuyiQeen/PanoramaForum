package com.example.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dao.entity.IdPoolEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IdPoolMapper extends BaseMapper<IdPoolEntity> {
    @Select("SELECT user_id FROM id_pool WHERE is_used = 0 ORDER BY user_id LIMIT 1")
    Integer selectNextUnused();
}
