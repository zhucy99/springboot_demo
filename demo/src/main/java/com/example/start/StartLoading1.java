package com.example.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 服务启动的时候运行
 * 创建一个类实现 CommandLineRunner 接口，重写 run(String... arg0)方法
 * @author Chongyang
 */
@Component
@Order(value=1) //优先级 
public class StartLoading1 implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
    	System.out.println("优先级：1 ********* StartLoading1：服务启动的时候运行，正在加载数据。。。。。");
    }
}
