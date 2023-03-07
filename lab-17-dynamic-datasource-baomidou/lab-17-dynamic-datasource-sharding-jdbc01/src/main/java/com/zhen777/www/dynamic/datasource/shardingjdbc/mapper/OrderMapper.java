package com.zhen777.www.dynamic.datasource.shardingjdbc.mapper;


import com.zhen777.www.dynamic.datasource.shardingjdbc.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    OrderDO selectById(@Param("id") Integer id);

}
