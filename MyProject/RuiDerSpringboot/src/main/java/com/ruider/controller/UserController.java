package com.ruider.controller;

/**
 * Created by mahede on 2018/11/27.
 */
import com.ruider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by Administrator on 2018\9\8 0008.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public  boolean login (String username, String password){
        System.out.println ( "微信小程序调用接口！！！用户名:" + username + "密码:" + password );
        boolean login = userService.login ( username, password );
        if (login) {
            return true;
        }
        return false;
    }
}
