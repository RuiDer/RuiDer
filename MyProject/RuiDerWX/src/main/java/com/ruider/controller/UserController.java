package com.ruider.controller;

/**
 * Created by mahede on 2018/11/27.
 */
import com.ruider.common.Result;
import com.ruider.model.User;
import com.ruider.utils.CommonUtils;
import com.ruider.utils.HttpRequest;
import net.sf.json.JSONObject;
import com.ruider.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018\9\8 0008.
 */
@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${APPID}")
    private String APPID;

    @Value("${APPSECRECT}")
    private String APPSECRECT;

    @Value("${GRANTTYPE}")
    private String GRANTTYPE;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("test")
    public Result test(String encryptedData, String iv, String code) {
        Result result = new Result();

        if (code == null || code.length() == 0) {
            result.setIsSuccess(false);
            result.setMessage("code不能为空");
            return result;
        }

        try{
            logger.info("editUserDetails start");

            String params = "appid=" + APPID + "&secret=" + APPSECRECT + "&js_code=" + code + "&grant_type=" + GRANTTYPE;
            //发送请求
            String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
            //解析相应内容（转换成json对象）
            JSONObject json = JSONObject.fromObject(sr);
            //获取会话密钥（session_key）
            String session_key = json.get("session_key").toString();
            //用户的唯一标识（openid）
            String openid = (String) json.get("openid");
            Map<String,String> userInfo = new HashMap<>();
            userInfo.put("session_key", session_key);
            userInfo.put("openid", openid);
            result.setIsSuccess(true);
            result.setData(userInfo);
            result.setMessage("获取微信用户信息成功");
            logger.info("【获取微信用户信息成功】test success");
            return result;
        }catch (Exception e) {
            logger.error("【获取微信用户信息成功】test fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("获取微信用户信息成功");
            return result;
        }
    }


    @RequestMapping("/toLogin")
    public  boolean login (String username, String password){
        System.out.println ( "微信小程序调用接口！！！用户名:" + username + "密码:" + password );
        boolean login = userService.login ( username, password );
        if (login) {
            return true;
        }
        return false;
    }

    /*@ResponseBody
    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET) // 获取用户信息
    public String getOpenId(@Param("code") String code, @RequestParam("headurl") String headurl,
                            @RequestParam("nickname") String nickname, @RequestParam("sex") String sex,
                            @RequestParam("country") String country, @RequestParam("province") String province,
                            @RequestParam("city") String city) {
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        try {
            if (StringUtils.isBlank(code)) {
                System.out.println("code为空");
            } else {
                String requestUrl = WX_URL.replace("APPID", APPID).replace("SECRET", APPSECRECT)
                        .replace("JSCODE", code).replace("authorization_code", GRANTTYPE);
                JSONObject jsonObject = CommonUtils.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null) {
                    try {
                        // 业务操作
                        String openid = jsonObject.getString("openid");
                        wechatService.selectUserByOpenId(openid, headurl, nickname, sex, country, province, city);
                        return openid;
                    } catch (Exception e) {
                        System.out.println("业务操作失败");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("code无效");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "错误";
    }*/

    @PostMapping(value = "addUserInfoIfNoSaved")
    public Result addUserInfoIfNoSaved (@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("addUserInfoIfNoSaved start");
            int ret = userService.addUser(paramMap);
            if(ret == -1) {
                result.setCode(Result.ALREADY_SAVED);
                result.setIsSuccess(false);
                result.setMessage("该用户已经被关注");
                logger.info("【用户信息已存在】addUserInfoIfNoSaved fail");
            }else{
                result.setIsSuccess(true);
                result.setMessage("用户信息保存成功");
                logger.info("【用户信息保存成功】addUserInfoIfNoSaved success");
            }

            return result;
        }catch (Exception e) {
            logger.error("【用户信息保存失败】addUserInfoIfNoSaved fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("用户信息保存失败");
            return result;
        }
    }

    @PostMapping(value = "getUserDetails")
    public Result getUserDetails(@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try {
            logger.info("getUserDetails start ");
            User user = userService.getUserDetails(paramMap);
            result.setIsSuccess(true);
            result.setData(user);
            result.setMessage("获取用户信息成功");
            logger.info("【获取用户信息成功】getUserDetails success, userName:" + paramMap.get("userName").toString());
            return result;
        }catch (Exception e){
            logger.error("【获取用户信息成功】getUserDetails fail, userName:" + paramMap.get("userName").toString(), e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("获取用户信息失败");
            return result;
        }
    }

    @PostMapping(value = "editUserDetails")
    public Result editUserDetails (@RequestBody HashMap<String,Object> paramMap) {
        Result result = new Result();
        try{
            logger.info("editUserDetails start");
            userService.updateUser(paramMap);
            result.setIsSuccess(true);
            result.setMessage("用户信息更新成功");
            logger.info("【用户信息更新成功】editUserDetails success");
            return result;
        }catch (Exception e) {
            logger.error("【用户信息更新失败】editUserDetails fail", e);
            result.setCode(Result.FAIL_CODE);
            result.setIsSuccess(false);
            result.setMessage("用户信息更新失败");
            return result;
        }
    }


}
