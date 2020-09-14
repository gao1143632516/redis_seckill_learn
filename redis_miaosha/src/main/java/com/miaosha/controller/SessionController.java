package com.miaosha.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gmq
 * @create 2020-09-13 15:13
 */
@RestController
public class SessionController {
    @Value("${server.port}")
    private int port;
    @RequestMapping("/session")
    public Map testSession(HttpSession session){
       if(session.getAttribute("gmq")==null){
           session.setAttribute("gmq","1235");
           System.out.println(session.getId());
       }
       HashMap<String, Object> map = new HashMap<>();
       map.put("gmq",session.getAttribute("gmq"));
       map.put("sesionid",session.getId());
       map.put("port",port);

        return map;
    }
}
