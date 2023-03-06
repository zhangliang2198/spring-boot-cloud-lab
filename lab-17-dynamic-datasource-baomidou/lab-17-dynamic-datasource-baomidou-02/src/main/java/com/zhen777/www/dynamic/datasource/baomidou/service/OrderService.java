package com.zhen777.www.dynamic.datasource.baomidou.service;

// OrderService.java

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zhen777.www.dynamic.datasource.baomidou.constant.DBConstants;
import com.zhen777.www.dynamic.datasource.baomidou.dataobject.OrderDO;
import com.zhen777.www.dynamic.datasource.baomidou.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @DS(DBConstants.DATASOURCE_MASTER)
    public void add(OrderDO order) {
        // 这里先假模假样的读取一下
        orderMapper.selectById(order.getId());

        // 插入订单
        orderMapper.insert(order);
    }

    public OrderDO findById(Integer id) {
        return orderMapper.selectById(id);
    }

}
