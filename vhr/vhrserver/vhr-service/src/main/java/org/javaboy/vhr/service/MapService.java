package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.MapMapper;
import org.javaboy.vhr.model.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MapService {
    @Autowired
    MapMapper mapMapper;

    public List<Map> getAllMaps() {
        return mapMapper.getAllMaps();
    }

    public Integer addMap(Map map) {
        map.setCreateDate(new Date());
        return mapMapper.insertSelective(map);
    }

    public Integer deleteMapById(Integer id) {
        return mapMapper.deleteByPrimaryKey(id);
    }

    public Integer updateMapById(Map map) {
        return mapMapper.updateByPrimaryKeySelective(map);
    }
}
