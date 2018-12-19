package com.ruider.service.impl;

/**
 * Created by mahede on 2018/11/27.
 */
import com.ruider.mapper.UserMapper;
import com.ruider.model.User;
import com.ruider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username,String password){
        User userEntity = new User ();
        userEntity.setUsername ( username );
        userEntity.setPassword ( password );

        User user = userMapper.selectUser ( userEntity );
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public int addUser(HashMap<String, Object> paramMap) throws Exception {
        User user = new User();
        user.setImage(paramMap.get("image").toString());
        user.setSex(paramMap.get("sex").toString());
        user.setUsername(paramMap.get("userName").toString());
        Date createTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(simpleDateFormat.format(createTime));
        if(userMapper.selectUser(user) == null) {
            return userMapper.addUserByWX(user);
        }else
            return -1;
    }

    @Override
    public User getUserDetails (HashMap<String,Object> paramMap) throws Exception {
        User user = new User();
        user.setUsername(paramMap.get("userName").toString());
        user.setSex(paramMap.get("sex").toString());
        user.setImage(paramMap.get("image").toString());
        user = userMapper.selectUser(user);
        if(user.getSex().equals("1")) user.setSex("男");
        else user.setSex("女");
        return user;

    }

    @Override
    public int updateUser(HashMap<String,Object> paramMap) throws Exception{
        User user = new User();
        user.setUsername(paramMap.get("userName").toString());
        user.setAge(Integer.valueOf(paramMap.get("age").toString()));
        user.setSex(paramMap.get("sex").toString());
        user.setMobilePhone(paramMap.get("phone").toString());
        user.setAutograph(paramMap.get("autograph").toString());
        user.setHeadurl(paramMap.get("headerUrl").toString());
        user.setRealName(paramMap.get("realName").toString());
        user.setId(Integer.valueOf(paramMap.get("id").toString()));
        return userMapper.updateUser(user);
    }

}