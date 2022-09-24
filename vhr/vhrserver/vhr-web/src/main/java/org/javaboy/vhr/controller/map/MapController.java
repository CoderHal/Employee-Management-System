package org.javaboy.vhr.controller.map;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Map;
import org.javaboy.vhr.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map/manage")
public class MapController {
    @Autowired
    MapService mapService;

    @GetMapping("/")
    public List<Map> getAllMaps() {
        return mapService.getAllMaps();
    }

    @PostMapping("/")
    public RespBean addMap(@RequestBody Map map) {
        if (mapService.addMap(map) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteMapById(@PathVariable Integer id) {
        if (mapService.deleteMapById(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateMapById(@RequestBody Map map) {
        if (mapService.updateMapById(map) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}