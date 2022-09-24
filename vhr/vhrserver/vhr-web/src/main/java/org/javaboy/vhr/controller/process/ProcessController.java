package org.javaboy.vhr.controller.process;

import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Process;
import org.javaboy.vhr.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study/process")
public class ProcessController {
    @Autowired
    ProcessService processService;

    @GetMapping("/")
    public List<Process> getAllProcesses() {
        return processService.getProcesses();
    }

    @PostMapping("/")
    public RespBean addProcess(@RequestBody Process process) {
        if (processService.addProcess(process) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteProcessById(@PathVariable Integer id) {
        if (processService.deleteProcessById(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateProcessById(@RequestBody Process process) {
        if (processService.updateProcessById(process) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}