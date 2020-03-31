package com.jdkcb.mybatisstuday.controller;

import com.jdkcb.mybatisstuday.mapper.UserMapper;
import com.jdkcb.mybatisstuday.mapper.one.PrimaryUserMapper;
import com.jdkcb.mybatisstuday.mapper.two.SecondaryUserMapper;
import com.jdkcb.mybatisstuday.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private PrimaryUserMapper primaryUserMapper;
    @Autowired
    private SecondaryUserMapper secondaryUserMapper;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("primary")
    public Object primary(){
        List<User> list = primaryUserMapper.findAll();
        return list;
    }
    @RequestMapping("secondary")
    public Object secondary(){
        List<User> list = secondaryUserMapper.findAll();
        return list;
    }

    /**
     * 第二种，使用aop拦截注解
     * 跟id,动态切换数据源
     * @param id
     * @return
     */
    @RequestMapping("third/{id}")
    public Object third(@PathVariable("id") int id){
        List<User> list;
        if(id == 2){
            list = userMapper.findAll2();
        }else{
            list = userMapper.findAll1();
        }
        return list;
    }


}
