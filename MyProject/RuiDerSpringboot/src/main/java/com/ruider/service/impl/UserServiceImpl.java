package com.ruider.service.impl;

/**
 * Created by mahede on 2018/11/27.
 */
import com.ruider.mapper.UserEntityMapper;
import com.ruider.model.User;
import com.ruider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public boolean login(String username,String password){
        User userEntity = new User ();
        userEntity.setUsername ( username );
        userEntity.setPassword ( password );

        User user = userEntityMapper.selectUser ( userEntity );
        if (user != null){
            return true;
        }
        return false;
    }

}