package com.zhanghao.web.common.constant;

/**
 * 
 * @author Administrator
 *
 */
public class SessionConstant {

	/**
	 * 登录用户的session键名.
	 */
	public static final String USER_SESSION_KEY = "pmsUser";

	/**
	 * 商户主帐号ID的session键名.
	 */
	public static final String MAIN_USER_ID_SESSION_KEY = "pmsMainUserId";

	/**
	 * 登录用户拥有的权限集合的session键名.
	 */
	public static final String ACTIONS_SESSION_KEY = "actions";

	/**
	 * 用户密码连续输错次数限制(默认5).
	 */
	public static int WEB_PWD_INPUT_ERROR_LIMIT = 5;

}
