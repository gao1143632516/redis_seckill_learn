package com.miaosha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gmq
 * @create 2020-09-10 17:08
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    @ResponseBody
    public Map<String, Object> index(@RequestParam(name="name",required = false,defaultValue = "gmq_moren")String name, int age){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        return map;
    }

}
