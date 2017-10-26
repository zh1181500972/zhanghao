package com.zhanghao.service.user.dao;

import com.zhanghao.facate.entity.PmsUser;
import com.zhanghao.service.user.common.core.dao.BaseDao;

/**
 * 
 * @描述:用户表数据访问层接口
 * @author zhanghao
 * @创建时间:2017年10月25日 下午3:42:06
 */
public interface PmsUserDao extends BaseDao<PmsUser> {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	PmsUser findByUserNo(String userNo);

}
