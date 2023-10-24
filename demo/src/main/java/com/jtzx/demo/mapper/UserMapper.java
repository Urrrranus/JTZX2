package com.jtzx.demo.mapper;

import com.jtzx.demo.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //注册新用户
    @Insert("""
            insert into users(username,password)
            values(#{username},#{password})
            """)
    void insert(User user);
    //用户登录
    @Select("""
            select * from users where username = #{uesrname}
            and password = #{password}
            """)
    User select (User user);
    //注册查询
    @Select("""
             select username,password from users
             where username = #{username}
             """)
      User check(String userName);
     // 储存token
    @Update("""
            update users set token = #{token}
            where username = #{username}
            """)
      void storeToken (String username);
    //校验token
    @Select("""
            select username from users 
            where token = #{token}
            """)
      String checktoken (String token);


}
