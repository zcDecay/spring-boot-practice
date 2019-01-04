package com.basic.transaction.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author zcc
 * @Date 19/01/03
 */
@Mapper
public interface AccountMapper {

    int updateAccount(@Param("money") double money, @Param("id") int  id);

}
