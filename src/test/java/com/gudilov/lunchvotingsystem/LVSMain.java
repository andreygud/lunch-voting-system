package com.gudilov.lunchvotingsystem;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LVSMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml");
    }
}
