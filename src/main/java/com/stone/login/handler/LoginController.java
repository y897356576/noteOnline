package com.stone.login.handler;

import com.stone.common.redis.RedisShard;
import com.stone.common.util.ResultMap;
import com.stone.core.factory.UserFactory;
import com.stone.core.model.User;
import com.stone.login.service.LoginService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 石头 on 2017/6/20.
 */
@Controller
@RequestMapping("/loginHandler")
public class LoginController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    LoginService loginServic;

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String userName, String passWord, HttpServletResponse response) {

        Map<String, Object> rsMap = new HashMap<String, Object>();

        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);

        try{
            UserFactory.standardUser(user);
            user = loginServic.doLogin(user, response);
        } catch (Exception e){
            user = null;
            rsMap = ResultMap.generateMap(false, e.getMessage());
        }

        if (user != null) {
            rsMap = ResultMap.generateMap(true);
            logger.info("[" + user.getUserName() + "]：登录成功");

        }
        return rsMap;
    }


    @RequestMapping(value = "doLogOut/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void doLogOut(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        loginServic.doLogOut(id, request, response);
    }
}
