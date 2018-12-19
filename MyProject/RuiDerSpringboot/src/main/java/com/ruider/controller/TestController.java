package com.ruider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mahede on 2018/11/22.
 */

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/test")
    public String Test(String tt){

        return "RuiDer";
    }


}
