package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.VideoMapper;
import org.javaboy.vhr.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;

    public List<Video> getAllVideos() {
        return videoMapper.getAllVideos();
    }

    public Integer addVideo(Video video) {
        return videoMapper.insertSelective(video);
    }

    public Integer deleteVideoById(Integer id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    public Integer updateVideoById(Video video) {
        return videoMapper.updateByPrimaryKeySelective(video);
    }
}
