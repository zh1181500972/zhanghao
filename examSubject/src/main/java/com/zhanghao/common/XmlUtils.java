package com.zhanghao.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.zhanghao.model.Subject;

import ch.qos.logback.core.net.SyslogOutputStream;

public class XmlUtils {
	private static final String DB_SUBJECT_XML = "db/subject.xml";

	public static void addXmlNode(Subject subject) throws Exception {
		Document doc = getXmlFileRootNode();
		Branch rootElement = doc.getRootElement();
		Element element = rootElement.addElement("subject");
		element.addAttribute("id", subject.getId());
		element.addElement("title").addText(subject.getTitle());
		element.addElement("context").addText(subject.getContext());
		element.addElement("createDate").addText(DateUtil.dateToStringWithTime(new Date()));
		element.addElement("updateDate").addText("");
		element.addElement("courseName").addText(subject.getCourseName());
		element.addElement("typaName").addText(subject.getTypeName());
		writeDocuemnt(doc);
	}

	public static void writeDocuemnt(Document doc) throws Exception {
		// 设置保存的格式化器 1. \t，使用什么来完成缩进 2. true, 是否要添加换行
		OutputFormat format = new OutputFormat("\t", true);
		format.setTrimText(true); // 去掉空白
		// 在创建Writer时，指定格式化器
		XMLWriter writer = new XMLWriter(new FileOutputStream(DB_SUBJECT_XML), format);
		writer.write(doc);
		writer.close();
	}

	public static Document getXmlFileRootNode(String filePath) throws Exception {
		SAXReader reader = new SAXReader();
		Document read = reader.read(new File(filePath));
		return read;
	}

	public static Document getXmlFileRootNode() throws Exception {
		SAXReader reader = new SAXReader();
		Document read = reader.read(new File(DB_SUBJECT_XML));
		return read;
	}

	public static void removeNode(String id) throws Exception {
		Document doc = getXmlFileRootNode();
		Element stuEle = (Element) doc.selectSingleNode("//subject[@id='" + id + "']");
		if (stuEle != null) {
			boolean remove = stuEle.getParent().remove(stuEle);
			writeDocuemnt(doc);
		}
	}

	public static void updataNode(Subject subject) throws Exception {
		Document doc = getXmlFileRootNode();
		Element stuEle = (Element) doc.selectSingleNode("//subject[@id='" + subject.getId() + "']");
		if (stuEle != null) {
			stuEle.element("title").setText(subject.getTitle());
			stuEle.element("context").setText(subject.getContext());
			stuEle.element("updateDate").setText(DateUtil.dateToStringWithTime(new Date()));
			stuEle.element("courseName").setText(subject.getCourseName());
			stuEle.element("typaName").setText(subject.getTypeName());
			writeDocuemnt(doc);
		}
	}
}
