package com.jtzx.demo.controller;

import com.jtzx.demo.dto.User;
import com.jtzx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    //注册用户
    @RequestMapping("/register")
    @ResponseBody
     public Object register(@RequestBody User user) {
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //用户登录
    @RequestMapping
    @ResponseBody
    public Object login(@RequestBody User user) {
       String token = userService.login(user);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
    @Autowired
    UserService userService;
}
