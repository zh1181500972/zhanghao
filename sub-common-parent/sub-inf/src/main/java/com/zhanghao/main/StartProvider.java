package com.zhanghao.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProvider {
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException {
		context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		context.start();
		System.out.println("dubbo service successful");
		System.in.read(); // 按任意键退出
	}
}