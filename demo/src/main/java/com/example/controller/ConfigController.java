package com.example.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Boot 教程三
 * 读取配置文件
 * config 下的 application.properties优先级要大于其他文件夹的配置文件
 * @author LingDu
 *
 */
@Controller
@RequestMapping(value=("/config"))
public class ConfigController {

    /**
     * 属性的值是通过配置文件读取的
     */
    @Value(value="${lingdu.secret}")  
    private String uuid;  

    @Value(value="${lingdu.number}")  
    private int randomID;  

    @Value(value="${lingdu.limitnumber}")  
    private int limitnumber;  
    
    @Value(value="${application.hello}")  
    private String hello;  

    /**
     * 2：返回JSON数据
     * @return list
     */
    @RequestMapping("/valueToJson")
    public @ResponseBody List<Object> getValueToJson(){
        List<Object> list = new ArrayList<>();
        list.add(this.uuid);
        list.add(this.randomID);
        list.add(this.limitnumber);
        list.add(this.hello);
        return list;
    }
}
