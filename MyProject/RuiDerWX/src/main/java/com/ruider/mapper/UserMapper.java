package com.ruider.mapper;

import com.ruider.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by mahede on 2018/11/27.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into `user`" +
            "(`username`, `age`, `password`, `mobilePhone`,`autograph`, `status`, `realName`, `sex`,`level`,`createTime`,`extendField1`，`extendField2`，`extendField3`，`extendField4`，`extendField5`，`extendField6`，`extendField7`) " +
            "values(#{username}, #{age}, #{password}, #{mobilePhone}, #{autograph}, #{status}, #{realName},#{sex},#{level},#{createTime},#{extendField1},#{extendField2},#{extendField3},#{extendField4},#{extendField5},#{extendField6},#{extendField7})")
    int addUser(User user);

    @Delete("delete from `user` where `id` = #{id}")
    int deleteUserById(int id);

    @Update("update `user` set `username` = #{userName},`age` = #{age}, `mobilePhone` = #{mobilePhone}, `autograph` = #{autograph}, `realName` = #{realName}, `sex` = #{sex}, `headUrl` = #{headUrl} where `id` = #{id} ")
    int updateUser(User user);

    @Select("select * from `RuiDer`.`user`" +
            " where `username`= #{username} and `sex` = #{sex} and `image` = #{image} ")
    User selectUser(User user);

    @Select("select * from `RuiDer`.`user`" +
            " where `openid` = #{openid}")
    User selectUserByOpenId(String openid);

    @Select("select * from `RuiDer`.`user` where `id` = #{id}")
    User selectUserById(int id);


    //更新头像
    @Update("update `user` set `image` = #{image} where `id` = #{id}")
    int updateImage(User user);

    //更新登录状态
    @Update("update `user` set `status` = #{status} where `id` = #{id}")
    int updateStatus(User user);

    //更新用户基本信息
    @Update("update `user` set `username` = #{nickname}, `headurl` = #{headurl}, `sex` = #{sex}, `userip` = #{userip} where `openid` = #{openid}")
    int updateUserMseeage(String openid, String nickname, String headurl, String sex, String userip);

    @Insert("insert into `user`(`username`, `sex`, `createTime`, `image`) values(#{username}, #{sex},  #{createTime}, #{image})")
    int addUserByWX(User user);
}
