package com.zhanghao.model;

import org.springframework.web.servlet.ModelAndView;

public class AjaxMessage {
	private int statusCode;
	private String message;
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;
	private String confirmMsg;

	public AjaxMessage ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "");
	}

	public AjaxMessage ajaxDoneError(String message) {
		return ajaxDone(300, message, "");
	}
   
	public AjaxMessage ajaxDone(int statusCode, String message, String forwardUrl) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		ajaxMessage.setStatusCode(statusCode);
		ajaxMessage.setMessage(message);
		ajaxMessage.setForwardUrl(forwardUrl);
		return ajaxMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

}
