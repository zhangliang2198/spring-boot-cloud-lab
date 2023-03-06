package com.zhen777.www.dynamic.datasource.mybatis.mapper.orders;

import com.zhen777.www.dynamic.datasource.mybatis.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    OrderDO selectById(@Param("id") Integer id);

}
