package com.zhanghao.xmltest;

import java.util.UUID;

import org.dom4j.Document;
import org.junit.Test;

import com.zhanghao.common.XmlUtils;
import com.zhanghao.model.Subject;

public class XmlUtilsTest {
	@Test
	public void writeXml() throws Exception {
		Document doc = XmlUtils.getXmlFileRootNode("db/subject.xml");
		Subject subject = new Subject();
		subject.setId(UUID.randomUUID().toString());
		subject.setTitle("标题1");
		subject.setContext("<h3 style=\"color:red\">副本文本</h3>");
		subject.setCourseName("数学");
		//XmlUtils.addXmlNode(subject, doc.getRootElement());
		XmlUtils.writeDocuemnt(doc);
	}
	
	@Test
	public void removeDate() throws Exception {
		XmlUtils.removeNode("d3ec4fe5-863c-43b7-8c69-1ac1577805f0");
	}
}
