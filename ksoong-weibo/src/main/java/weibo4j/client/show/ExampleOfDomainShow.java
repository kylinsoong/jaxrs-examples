package weibo4j.client.show;

import java.io.IOException;

import org.ksoong.weibo.Token;
import org.ksoong.weibo4j.User;

public class ExampleOfDomainShow {

    public static void main(String[] args) throws IOException {

        User user = new User(Token.TOKEN);
        System.out.println(user.domain_show("teiid"));
        System.out.println(user.domain_show("kylinsoong"));
    }

}
