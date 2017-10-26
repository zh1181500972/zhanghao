package com.zhanghao.facate.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghao.facate.common.page.PageBean;
import com.zhanghao.facate.common.page.PageParam;
import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.facate.service.PmsUserService;
import com.zhanghao.service.user.biz.PmsUserBiz;

/**
 * @描述:用户Dubbo服务接口实现 
 * @author zhanghao
 * @创建时间:2017年10月25日 下午3:18:24
 */
@Service("pmsUserService")
public class PmsUserServiceImpl implements PmsUserService {
	@Autowired
	private PmsUserBiz pmsUserBiz;
	
	/**
	 * 保存用户信息.
	 * @param pmsUser
	 */
	public void create(PmsUser pmsUser) {
		pmsUserBiz.create(pmsUser);
	}
	
	/**
	 * 根据ID获取用户信息.
	 * @param userId
	 * @return
	 */
	public PmsUser getById(Long userId) {
		return pmsUserBiz.getById(userId);
	}

	/**
	 * 根据登录名取得用户对象
	 */
	public PmsUser findUserByUserNo(String userNo) {
		return pmsUserBiz.findUserByUserNo(userNo);
	}

	/**
	 * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除.
	 * 
	 * @param id
	 *            用户ID.
	 */
	public void deleteUserById(long userId) {
		pmsUserBiz.deleteUserById(userId);
	}

	
	/**
	 * 更新用户信息.
	 * @param user
	 */
	public void update(PmsUser user) {
		pmsUserBiz.update(user);
	}
	
	/**
	 * 根据用户ID更新用户密码.
	 * 
	 * @param userId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		pmsUserBiz.updateUserPwd(userId, newPwd, isTrue);
	}



	/**
	 * 查询并分页列出用户信息.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return pmsUserBiz.listPage(pageParam, paramMap);
	}
}
