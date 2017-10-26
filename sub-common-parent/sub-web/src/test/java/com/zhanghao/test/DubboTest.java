package com.zhanghao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.facate.service.PmsUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-context.xml")
public class DubboTest {
	@Autowired
	PmsUserService service;

	@Test
	public void test() {
		PmsUser findUserByUserNo = service.findUserByUserNo("admin");
		System.out.println(findUserByUserNo);
	}
}
