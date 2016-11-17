package weibo4j.client.common;

import java.io.IOException;

import org.ksoong.weibo.Token;
import org.ksoong.weibo4j.Common;

public class ExampleOfGetTimezone {

    public static void main(String[] args) throws IOException {

        Common common = new Common(Token.TOKEN);
        System.out.println(common.getTimeZone());
        System.out.println(common.getTimeZone("zh-tw"));
    }

}
