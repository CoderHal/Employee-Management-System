package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.ProcessMapper;
import org.javaboy.vhr.model.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcessService {
    @Autowired
    ProcessMapper processMapper;

    public List<Process> getProcesses() {
        return processMapper.getAllProcesses();
    }

    public Integer addProcess(Process process) {
        process.setCreateDate(new Date());
        return processMapper.insertSelective(process);
    }

    public Integer deleteProcessById(Integer id) {
        return processMapper.deleteByPrimaryKey(id);
    }

    public Integer updateProcessById(Process process) {
        return processMapper.updateByPrimaryKeySelective(process);
    }
}
