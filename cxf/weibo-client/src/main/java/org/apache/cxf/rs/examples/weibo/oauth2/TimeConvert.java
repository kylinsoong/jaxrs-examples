package org.apache.cxf.rs.examples.weibo.oauth2;

import java.util.Date;

public class TimeConvert {

    public static void main(String[] args) {
        
        /*
         * 1445576505390
         * 1445261932000
         * 1573653940000
         */

        System.out.println("current: " + new Date().getTime());
        System.out.println("create_at: " + new Date(1445261932000L));
        System.out.println("expire_at: " + new Date(1573653940000L));
    }

}
