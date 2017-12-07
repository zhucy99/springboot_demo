package com.example.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * @author LingDu
 */
@Component
@Order(value=2) //优先级 
public class StartLoading2 implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("优先级：2 ********* StartLoading1：服务启动的时候运行，正在加载数据。。。。。");
    }
}
