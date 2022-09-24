package org.javaboy.vhr.controller.video;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Video;
import org.javaboy.vhr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/")
    public List<Video> getAllMaps() {
        return videoService.getAllVideos();
    }

    @PostMapping("/")
    public RespBean addVideo(@RequestBody Video video) {
        if (videoService.addVideo(video) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteVideoById(@PathVariable Integer id) {
        if (videoService.deleteVideoById(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateVideoById(@RequestBody Video video) {
        if (videoService.updateVideoById(video) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}