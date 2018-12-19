package com.ruider.mapper;

import com.ruider.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mahede on 2018/11/27.
 */
@Mapper
public interface UserEntityMapper {

    @Select("select * from `user` where `username` = #{username} and `password` = #{password}")
    User selectUser(User userEntity);
}
