package com.zhanghao.web.controller;

import com.zhanghao.model.AjaxMessage;

public class BaseController {
	public AjaxMessage ajaxSuccess(String message) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		return ajaxMessage.ajaxDoneSuccess(message);
	}
}
