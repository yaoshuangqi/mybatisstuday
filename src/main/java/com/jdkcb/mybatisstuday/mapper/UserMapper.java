package com.jdkcb.mybatisstuday.mapper;

import com.jdkcb.mybatisstuday.anno.DataSource;
import com.jdkcb.mybatisstuday.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    @DataSource
    List<User> findAll1();


    @DataSource("secondary")//指定数据源为:secondary
    List<User> findAll2();
}
