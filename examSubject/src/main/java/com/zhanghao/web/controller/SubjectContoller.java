package com.zhanghao.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhanghao.model.AjaxMessage;
import com.zhanghao.model.Subject;
import com.zhanghao.service.SubjectServices;

@Controller
public class SubjectContoller extends BaseController {
	@Autowired
	SubjectServices subjectServices;
	
	
	@RequestMapping("/hellow")
	public String hellow(Model model) {
		model.addAttribute("name", "傻逼");
		model.addAttribute("totalCount", 100);
		return "index";
	}
	
	@RequestMapping("/demo_page1")
	public String demo_page1(Model model) {
		model.addAttribute("totalCount", 100);
		List<Subject> subjects=new ArrayList<Subject>();
		for(int i=0;i<=10;i++) {
			Subject subject = new Subject();
			subject.setId(UUID.randomUUID().toString());
			subject.setTitle("标题"+i);
			subject.setCreateDate(new  Date().toLocaleString());
			subject.setUpdateDate(new  Date().toLocaleString());
			subject.setContext("内容发送到发啊所所所所所所所所所所所所所所所所所所所所所所所所所所所所付多撒付的撒范德萨三国杀大概多少范德萨的法国大使馆的水果蛋糕adgas的gas的gas的gas的gas大嘎达噶爱国阿嘎时代感的丧失的嘎达感受得到"+i);
			subjects.add(subject);
		}
		model.addAttribute("subjects", subjects);
		return "demo_page1";
	}
	

	@RequestMapping("/demo_page4")
	public String demo_page4(Model model) {
		model.addAttribute("totalCount", 100);
		List<Subject> subjects=new ArrayList<Subject>();
		for(int i=0;i<=10;i++) {
			Subject subject = new Subject();
			subject.setId(UUID.randomUUID().toString());
			subject.setTitle("标题"+i);
			subject.setCreateDate(new  Date().toLocaleString());
			subject.setUpdateDate(new  Date().toLocaleString());
			subject.setContext("内容发送到发啊所所所所所所所所所所所所所所所所所所所所所所所所所所所所付多撒付的撒范德萨三国杀大概多少范德萨的法国大使馆的水果蛋糕adgas的gas的gas的gas的gas大嘎达噶爱国阿嘎时代感的丧失的嘎达感受得到"+i);
			subjects.add(subject);
		}
		model.addAttribute("subjects", subjects);
		return "demo_page4";
	}
	
	@RequestMapping("/addSubject")
	@ResponseBody
	public AjaxMessage addSubject(Subject subject) {
		subject.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		boolean insert = subjectServices.insert(subject);
		if(insert) {
			return ajaxSuccess("添加成功");
		}else {
			return ajaxSuccess("添加失败");
		}
	}
	
}
