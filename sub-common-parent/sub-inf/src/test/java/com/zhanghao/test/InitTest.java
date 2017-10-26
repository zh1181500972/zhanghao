package com.zhanghao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.facate.service.PmsUserService;

public class InitTest {
	@Test
	public void init() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/spring-context.xml");
		PmsUserService bean = context.getBean(PmsUserService.class);
		PmsUser byId = bean.findUserByUserNo("admin");
		System.out.println(byId.getEmail());
	}
	@Test
	public void initInsert() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/spring-context.xml");
		PmsUserService bean = context.getBean(PmsUserService.class);
		PmsUser pmsUser=new PmsUser();
		pmsUser.setUserNo("list");
		pmsUser.setUserName("张浩");
		pmsUser.setStatus(100);
		pmsUser.setVersion(0);
		pmsUser.setUserType("1");
		pmsUser.setUserPwd("123456");
		//int i=1/0;
		bean.create(pmsUser);
	}
}
