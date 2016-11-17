package org.ksoong.weibo;

public class SimpleClient {

    public static void main(String[] args) throws Exception {

        WeiboClient client = new WeiboClient(Token.TOKEN);
//        client.statuses_update("微博客户端测试");
//        client.statuses_upload("富春山居图", "/home/kylin/Pictures/test.jpg");
        client.statuses_upload_url_text("JBoss Modules 加载服务的实现分析 - http://ksoong.org/jboss-modules-services", "http://ksoong.org/assets/blog/wildfly/server-side-programming.png", "server-side-programming.png");
    }

}
