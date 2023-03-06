package com.zhen777.www.dynamic.datasource.baomidou.service;

// OrderService.java

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zhen777.www.dynamic.datasource.baomidou.constant.DBConstants;
import com.zhen777.www.dynamic.datasource.baomidou.mapper.OrderMapper;
import com.zhen777.www.dynamic.datasource.baomidou.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    private OrderService self() {
        return (OrderService) AopContext.currentProxy();
    }

    public void method01() {
        // ... 省略代码
    }

    @Transactional
    public void method02() {
        // ... 省略代码
    }

    public void method03() {
        // ... 省略代码
    }

    public void method04() {
        // ... 省略代码
    }

    @Transactional
    @DS(DBConstants.DATASOURCE_ORDERS)
    public void method05() {
        // ... 省略代码
    }

}
