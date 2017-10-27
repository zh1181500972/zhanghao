package com.zhanghao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhanghao.web.base.BaseController;

@Controller
public class LoginController extends BaseController {
	@RequestMapping("mainUI")
	public String mainUI() {
		System.out.println("into mainUI");
        return "index";
	}
}
