package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Register")
public class RegisterController {
    @Autowired
    HrService hrService;
    @PostMapping("/")
    public RespBean addHr(@RequestBody Hr hr){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(hr.getPassword());
        hr.setPassword(password);
        if(hrService.addHr(hr)==1) {
            return RespBean.ok("添加成功");
        }
        else {
            return  RespBean.error("添加失败");
        }
    }

}
