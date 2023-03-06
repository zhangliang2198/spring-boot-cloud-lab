package com.zhen777.www.dynamic.datasource.baomidou.dataobject;

import lombok.Data;

@Data
public class OrderDO {

    /**
     * 订单编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;

    // 省略 setting/getting 方法

}
