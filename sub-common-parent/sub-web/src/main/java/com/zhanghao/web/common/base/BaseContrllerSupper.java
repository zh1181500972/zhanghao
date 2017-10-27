package com.zhanghao.web.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhanghao.facate.common.page.PageBean;
import com.zhanghao.facate.common.page.PageParam;
import com.zhanghao.web.common.themes.dwz.DwzParam;


/**
 * controller的基础支撑类，所有类必须基础该类
 * @author zhanghao
 * @时间  @2017年10月27日 上午10:28:53
 *
 */
public class BaseContrllerSupper {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * pageBean.
	 * 
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return (PageBean) request.getAttribute("pageBean");
	}


	/**
	 * 取得会话session
	 * 
	 * @return session
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * 获取session里面的对应属性的值
	 * 
	 * @return
	 */
	public Object getSessionValue(String key) {
		return getSession().getAttribute(key);
	}
	
	/**
	 * 获取requeset里面的对应属性的值
	 * 
	 * @return
	 */
	public Object getRequestValue(String key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 获取application中的值
	 */
	public Object getApplicationValue(String key) {
		return getSession().getServletContext().getAttribute("key");
	}

	/**
	 * 取得当前web应用的根路径
	 * 
	 * @return
	 */
	public String getWebRootPath() {
		return getSession().getServletContext().getRealPath("/");
	}

	// ////////////////////////////////////////////////////////////////////////
	// /////////////// 添加结合DWZ-UI框架的Action层可共用方法 //////////////////////
	// /////////////// zhanghao 2017-10-27 /////////////////////////////////
	/**
	 * 响应DWZ-UI的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图并提示“操作成功”.
	 * 
	 * @author zhanghao.
	 * @param message
	 *            提示消息.
	 * @return DwzParam .
	 */
	public DwzParam operateSuccess() {
		return ajaxDone("200", "操作成功");
	}

	/**
	 * 响应DWZ的Ajax成功请求（statusCode="200"）,<br/>
	 * 跳转到operateSuccess视图，提示设置的消息内容.
	 * 
	 * @author zhanghao.
	 * @param message
	 *            提示消息.
	 * @return DwzParam .
	 */
	public DwzParam operateSuccess(String message) {
		return ajaxDone("200", message);
	}

	/**
	 * 响应DWZ的ajax失败请求（statusCode="300"）,跳转到ajaxDone视图.
	 * 
	 * @author zhanghao.
	 * @param message
	 *            提示消息.
	 * @return ajaxDone .
	 */
	public DwzParam operateError(String message) {
		return ajaxDone("300", message);
	}

	/**
	 * 响应DWZ的Ajax请求.
	 * 
	 * @author zhanghao.
	 * @param statusCode
	 *            statusCode:{ok:200, error:300, timeout:301}.
	 * @param message
	 *            提示消息.
	 */
	private DwzParam ajaxDone(String statusCode, String message) {
		return getDwzParam(statusCode, message);
	}

	/**
	 * 根据request对象，获取页面提交过来的DWZ框架的AjaxDone响应参数值.
	 * 
	 * @author zhanghao.
	 * @param statusCode
	 *            状态码.
	 * @param message
	 *            操作结果提示消息.
	 * @return DwzParam :封装好的DwzParam对象 .
	 */
	public DwzParam getDwzParam(String statusCode, String message) {
		// 获取DWZ Ajax响应参数值,并构造成参数对象
		String navTabId = request.getParameter("navTabId");
		String dialogId = request.getParameter("dialogId");
		String callbackType = request.getParameter("callbackType");
		String forwardUrl = request.getParameter("forwardUrl");
		String rel = request.getParameter("rel");
		return new DwzParam(statusCode, message, navTabId, dialogId, callbackType, forwardUrl, rel, null);
	}

	// ///////////////////////////////////////////////////////////////
	// ///////////////// 结合DWZ-UI的分页参数获取方法 ///////////////////////////
	/**
	 * 获取当前页（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回1.
	 * 
	 * @author zhanghao.
	 */
	private int getPageNum() {
		// 当前页数
		String pageNumStr = request.getParameter("pageNum");
		int pageNum = 1;
		if (StringUtils.isNotBlank(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		return pageNum;
	}

	/**
	 * 获取每页记录数（DWZ-UI分页查询参数）.<br/>
	 * 如果没有值则默认返回15.
	 * 
	 * @author zhanghao.
	 */
	private int getNumPerPage() {
		String numPerPageStr = request.getParameter("numPerPage");
		int numPerPage = 15;
		if (StringUtils.isNotBlank(numPerPageStr)) {
			numPerPage = Integer.parseInt(numPerPageStr);
		}
		return numPerPage;
	}

	/**
	 * 获取分页参数，包含当前页、每页记录数.
	 * 
	 * @return PageParam .
	 */
	public PageParam getPageParam() {
		return new PageParam(getPageNum(), getNumPerPage());
	}

	// //////////////////////// 存值方法 /////////////////////////////////

	/**
	 * 将数据放入request中.<br/>
	 */
	public void putData(String key, Object value) {
		request.setAttribute(key, value);
	}

	// ///////////////////////getHttpRequest()方法扩展//////////////////////////
	/**
	 * 根据参数名从HttpRequest中获取Double类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return DoubleValue or null .
	 */
	public Double getDouble(String key) {
		String value = request.getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}
	
	/**
	 * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return IntegerValue or null .
	 */
	public Integer getInteger(String key) {
		String value = request.getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = request.getParameter(key);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return String or null .
	 */
	public String getString(String key) {
		return request.getParameter(key);
	}

	/**
	 * 与DWZ框架结合的表单属性长度校验方法.
	 * 
	 * @param propertyName
	 *            要校验的属性中文名称，如“登录名”.
	 * @param property
	 *            要校验的属性值，如“gzzyzz”.
	 * @param isRequire
	 *            是否必填:true or false.
	 * @param minLength
	 *            最少长度:大于或等于0，如果不限制则可请设为0.
	 * @param maxLength
	 *            最大长度:对应数据库字段的最大长度，如不限制则可设为0.
	 * @return 校验结果消息，校验通过则返回空字符串 .
	 */
	protected String lengthValidate(String propertyName, String property, boolean isRequire, int minLength,
			int maxLength) {

		int propertyLenght = strLengthCn(property);
		if (isRequire && propertyLenght == 0) {
			return propertyName + "不能为空，"; // 校验不能为空
		} else if (isRequire && minLength != 0 && propertyLenght < minLength) {
			return propertyName + "不能少于" + minLength + "个字符，"; // 必填情况下校验最少长度
		} else if (maxLength != 0 && propertyLenght > maxLength) {
			return propertyName + "不能多于" + maxLength + "个字符，"; // 校验最大长度
		} else {
			return ""; // 校验通过则返回空字符串 .
		}
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为3位 ，当字符串为空时返回0.
	 * 
	 * @param str
	 *            字符串 .
	 * @return 字符串的长度 .
	 */
	private int strLengthCn(String str) {
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		int valueLength = 0;
		final String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int num = 0; num < str.length(); num++) {
			/* 获取一个字符 */
			final String temp = str.substring(num, num + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为3 */
				valueLength += 3;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

}
