package com.zhen777.www.dynamic.datasource.baomidou.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zhen777.www.dynamic.datasource.baomidou.constant.DBConstants;
import com.zhen777.www.dynamic.datasource.baomidou.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    @DS(DBConstants.DATASOURCE_SLAVE)
    OrderDO selectById(@Param("id") Integer id);

    @DS(DBConstants.DATASOURCE_MASTER)
    int insert(OrderDO entity);

}
