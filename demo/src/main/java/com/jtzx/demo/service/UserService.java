package com.jtzx.demo.service;

import com.jtzx.demo.dto.User;
import com.jtzx.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // 注册新用户, 用户名不能重名
    public void register(User user) {
        String userName = user.getUserName();
        User user1 = userMapper.check(userName);
        if (user1 != null) {
            throw new RuntimeException("用户名已存在");
        } else {
            userMapper.insert(user);
        }
    }

    //用户登录
    public String login(User user) throws UsernameNotFoundException {
        //用户名和密码的双条件查询，找得到就说明正确
        User user2 = userMapper.select(user);
        String token;
        if (user2 == null) {
            throw new UsernameNotFoundException("该用户不存在或登录密码错误");
        } else {
            //储存token，从而与对应用户关联
            token = String.valueOf(UUID.randomUUID());
            userMapper.storeToken(user.getUserName());
            return token;
        }

    }

    /*token校验，如果找得到对应的用户名，
    说明token在数据库中，也就说明token有效*/
    public void checktoken(String token) {
        String username = userMapper.checktoken(token);
        if (username == null) {
            throw new IllegalArgumentException("Token is invalid");
        }
    }

    /*通过token找到对应用户名（调用的checktoken方法，
    这个方法就是在数据库中用token搜索，找到对应的用户名*/
    public String search(String token) {
        return userMapper.checktoken(token);
    }

    //用户是否有权修改或删除（用户不能重名，而作者名与用户名相同，这可以用来判断是否有权限）
    public Boolean authentication(String token, String auther) {
        Boolean is = false;
        String userName = userMapper.checktoken(token);
        if (userName.equals(auther)) {
            is = true;
        }
        return is;
    }
}

