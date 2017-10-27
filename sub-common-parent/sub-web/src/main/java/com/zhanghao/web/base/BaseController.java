package com.zhanghao.web.base;

import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.web.common.base.BaseContrllerSupper;
import com.zhanghao.web.common.constant.SessionConstant;

public class BaseController extends BaseContrllerSupper {
	/**
	 * 获取当前登录的用户对象
	 * @return
	 */
	public PmsUser getLoginedUser() {
		return (PmsUser) getSessionValue(SessionConstant.USER_SESSION_KEY);
	}
}
