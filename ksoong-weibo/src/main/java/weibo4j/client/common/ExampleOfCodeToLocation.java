package weibo4j.client.common;

import java.io.IOException;

import org.ksoong.weibo.Token;
import org.ksoong.weibo4j.Common;

public class ExampleOfCodeToLocation {

    public static void main(String[] args) throws IOException {

        Common common = new Common(Token.TOKEN);
        System.out.println(common.code_to_location("001,002,003,004,005,006"));
    }

}
