package org.apache.cxf.rs.examples;

import javax.ws.rs.core.Form;

import org.apache.cxf.jaxrs.client.WebClient;

public class WeiboStatusUpdate {
    
    static String statuses_update = "https://api.weibo.com/2/statuses/update.json";

	public static void main(String[] args) {

	    WebClient wc = WebClient.create(statuses_update);
	    wc.form(new Form().param("status", "Apache CXF 微博客户端(http://ksoong.org/javaee/2015/10/20/cxf-weibo-client/) - Apache CXF (http://cxf.apache.org/) 实现了 JAX-RS 标准，基于JAX-RS 在服务器端，客户端，安全方面等都提供了很好的实现。本文演示如何通过 Apache CXF 客户端实现一个发送微博的小应用。").param("access_token", "2.00PZtDyBBfC2OE91c7a884547pNg4E"));
	}


}
