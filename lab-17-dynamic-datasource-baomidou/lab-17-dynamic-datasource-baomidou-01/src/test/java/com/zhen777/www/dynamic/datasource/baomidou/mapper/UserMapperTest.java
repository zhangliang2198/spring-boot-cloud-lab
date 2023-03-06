package com.zhen777.www.dynamic.datasource.baomidou.mapper;

import com.zhen777.www.dynamic.datasource.baomidou.Application;
import com.zhen777.www.dynamic.datasource.baomidou.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// UserMapperTest.java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectById() {
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }

}
