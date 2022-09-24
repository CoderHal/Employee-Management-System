package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.Map;

import java.util.List;

public interface MapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Map record);

    int insertSelective(Map record);

    Map selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Map record);

    int updateByPrimaryKey(Map record);

    List<Map> getAllMaps();
}