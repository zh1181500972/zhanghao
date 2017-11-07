package com.zhanghao.application;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.protocol.HTTP;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.html.HTMLTableSectionElement;

import ch.qos.logback.core.net.SyslogOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamSubjectApplicationTests {
	public static final String SOLR_URL = "http://localhost:8983/solr/";
	@Autowired
	private SolrClient client;

	@Test
	public void contextLoads() throws SolrServerException, IOException {
	}

	// solrServer是线程安全的，所以在使用时需要使用单例的模式，减少资源的消耗
	public HttpSolrServer getSolrServer() {
		Object solrServer = null;
		if (solrServer == null) {
			solrServer = new HttpSolrServer(SOLR_URL);
		}
		return (HttpSolrServer) solrServer;
	}

	public HttpSolrClient createSolrServer() {
		HttpSolrClient solr = null;
		solr = new HttpSolrClient(SOLR_URL);
		return solr;
	}
	 /**
     * 往索引库添加文档
    * @throws IOException 
    * @throws SolrServerException 
     */
    public void addDoc1() throws SolrServerException, IOException{
       //构造一篇文档  
        SolrInputDocument document = new SolrInputDocument();  
        //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义  
        document.addField("id", "8");  
        document.addField("name", "周新星");  
        //document.addField("description", "一个灰常牛逼的军事家");  
        //获得一个solr服务端的请求，去提交  ,选择具体的某一个solr core
       HttpSolrClient solr = new HttpSolrClient(SOLR_URL + "orderSercher");
       solr.add(document);
       solr.commit();
       solr.close();
    }
    
    @Test
    public void query() throws Exception{
    	SolrClient solr =new HttpSolrClient(SOLR_URL+"orderSercher");
    	SolrQuery query=new SolrQuery();
    	query.set("q", "*:*");
    	QueryResponse response = solr.query(query);
    	SolrDocumentList list = response.getResults();
    	Iterator<SolrDocument> iterator = list.iterator();
    	while(iterator.hasNext()) {
    		SolrDocument next = iterator.next();
    		Object fieldValue = next.getFieldValue("id");
    		System.out.println(fieldValue.toString());
    	}
    	System.out.println(list);
    }
    
    @Test
    public void addDoc() throws Exception {
    	SolrClient solr =new HttpSolrClient(SOLR_URL+"orderSercher");
    	SolrInputDocument document =new SolrInputDocument();
    	document.addField("id","552199");
    	document.addField("name","张浩");
    	document.addField("price","49.99");
    	UpdateResponse response = solr.add(document);// Remember to commit your changes!
    	solr.commit();
    	solr.close();
    }

}
