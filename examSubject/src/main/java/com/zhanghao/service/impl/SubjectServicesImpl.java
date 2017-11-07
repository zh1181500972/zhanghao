package com.zhanghao.service.impl;

import org.springframework.stereotype.Service;

import com.zhanghao.common.XmlUtils;
import com.zhanghao.model.Subject;
import com.zhanghao.service.SubjectServices;

@Service("subjectServices")
public class SubjectServicesImpl implements SubjectServices {

	@Override
	public boolean insert(Subject subjectje) {
		// TODO Auto-generated method stub
		try {
			XmlUtils.addXmlNode(subjectje);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Subject subjectje) {
		// TODO Auto-generated method stub
		try {
			XmlUtils.removeNode(subjectje.getId());
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Subject subject) {
		// TODO Auto-generated method stub
		try {
			XmlUtils.updataNode(subject);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
