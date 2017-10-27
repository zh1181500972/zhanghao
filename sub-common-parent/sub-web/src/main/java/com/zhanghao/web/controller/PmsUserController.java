package com.zhanghao.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghao.facate.common.page.PageBean;
import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.facate.service.PmsUserService;
import com.zhanghao.web.base.BaseController;
import com.zhanghao.web.common.base.BaseContrllerSupper;
import com.zhanghao.web.common.themes.dwz.DwzParam;



/**
 * 
 * @描述: 用户信息管理 .
 * @作者: WuShuicheng .
 * @创建时间: 2015-1-25,下午9:36:46 .
 * @版本号: V1.0 .
 */
@RequestMapping("/pmsUser")
@Controller
public class PmsUserController extends BaseController {

	private static Log log = LogFactory.getLog(PmsUserController.class);

	@Autowired
	private PmsUserService pmsUserService;

	// /////////////////////////////////// 用户管理   //////////////////////////////////////////
	/**
	 * 分页列出用户信息，并可按登录名获姓名进行查询.
	 * 
	 * @return listPmsUser or operateError .
	 * 
	 */
	@RequestMapping("/listPmsUser")
	public String listPmsUser(Map<String, Object> paramMap) {
		try {
			PageBean pageBean = pmsUserService.listPage(getPageParam(), paramMap);
			putData("pageBean", pageBean);
			PmsUser pmsUser = getLoginedUser();// 获取当前登录用户对象
			//this.putData("currUserNo", pmsUser.getUserNo());
			// 回显查询条件值
			putData("paramMap",paramMap);

			return "pms/PmsUserList";
		} catch (Exception e) {
			log.error("== listPmsUser exception:", e);
			return "";
		}
	}

	/**
	 * 查看用户详情.
	 * 
	 * @return .
	 */
	@RequestMapping("/viewPmsUser")
	public DwzParam viewPmsUserUI(Long userId) {
		try {
			PmsUser pmsUser = pmsUserService.getById(userId);
			if (pmsUser == null) {
				return operateError("无法获取要查看的数据");
			}

			putData("pmsUser",pmsUser);
			return operateSuccess();
		} catch (Exception e) {
			log.error("== viewPmsUserUI exception:", e);
			return operateError("获取数据失败");
		}
	}

	/**
	 * 转到添加用户页面 .
	 * 
	 * @return addPmsUserUI or operateError .
	 */
	@RequestMapping("/addPmsUserUI")
	public DwzParam addPmsUserUI() {
		try {
			//this.putData("UserStatusEnumList", UserStatusEnum.values());
			return operateSuccess();
		} catch (Exception e) {
			log.error("== addPmsUserUI exception:", e);
			return operateError("获取角色列表数据失败");
		}
	}

	/**
	 * 保存一个用户
	 * 
	 */
	@RequestMapping("/addPmsUser")
	public DwzParam addPmsUser(PmsUser pmsUser) {
		try {
			String userNo=pmsUser.getUserNo();
			String userPwd=pmsUser.getUserPwd();
			// 表单数据校验
			String validateMsg = validatePmsUser(pmsUser);

			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg); // 返回错误信息
			}

			// 校验用户登录名是否已存在
			PmsUser userNoCheck = pmsUserService.findUserByUserNo(userNo);
			if (userNoCheck != null) {
				return operateError("登录名【" + userNo + "】已存在");
			}

			pmsUser.setUserPwd(DigestUtils.sha1Hex(userPwd)); // 存存前对密码进行加密

			pmsUserService.create(pmsUser);

			return operateSuccess();
		} catch (Exception e) {
			log.error("== addPmsUser exception:", e);
			return operateError("保存用户信息失败");
		}
	}

	/**
	 * 校验Pms用户表单数据.
	 * 
	 * @param PmsUser
	 *            用户信息.
	 * @param roleUserStr
	 *            关联的角色ID串.
	 * @return
	 */
	private String validatePmsUser(PmsUser user) {
		String msg = ""; // 用于存放校验提示信息的变量
		msg += lengthValidate("真实姓名", user.getUserName(), true, 2, 15);
		msg += lengthValidate("登录名", user.getUserName(), true, 3, 50);
		
		// 登录密码
		String userPwd = user.getUserPwd();
		String userPwdMsg = lengthValidate("登录密码", userPwd, true, 6, 50);
		/*
		 * if (StringUtils.isBlank(loginPwdMsg) &&
		 * !ValidateUtils.isAlphanumeric(loginPwd)) { loginPwdMsg +=
		 * "登录密码应为字母或数字组成，"; }
		 */
		msg += userPwdMsg;

		// 手机号码
		String mobileNo = user.getMobileNo();
		String mobileNoMsg = lengthValidate("手机号", mobileNo, true, 0, 12);
		msg += mobileNoMsg;

		// 状态
		Integer status = user.getStatus();
		if (status == null) {
			msg += "请选择状态，";
		} else if (status.intValue() < 100 || status.intValue() > 101) {
			msg += "状态值不正确，";
		}

		msg += lengthValidate("描述", user.getRemark(), true, 3, 100);
		return msg;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 * */
	@RequestMapping("/deleteUserStatus")
	public DwzParam deleteUserStatus(Long id) {
		pmsUserService.deleteUserById(id);
		return this.operateSuccess("操作成功");
	}

}
