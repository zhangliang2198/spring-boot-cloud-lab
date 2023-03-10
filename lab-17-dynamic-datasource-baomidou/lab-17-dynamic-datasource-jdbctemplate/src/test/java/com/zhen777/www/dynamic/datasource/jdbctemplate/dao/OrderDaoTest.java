package com.zhen777.www.dynamic.datasource.jdbctemplate.dao;

import com.zhen777.www.dynamic.datasource.jdbctemplate.Application;
import com.zhen777.www.dynamic.datasource.jdbctemplate.dataobject.OrderDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testSelectById() {
        OrderDO order = orderDao.selectById(1);
        System.out.println(order);
    }

}
