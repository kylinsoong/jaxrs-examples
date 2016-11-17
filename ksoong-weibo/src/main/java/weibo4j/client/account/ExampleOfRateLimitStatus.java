package weibo4j.client.account;

import java.io.IOException;

import org.ksoong.weibo.Token;
import org.ksoong.weibo4j.Account;

public class ExampleOfRateLimitStatus {

    public static void main(String[] args) throws IOException {

        Account account = new Account(Token.TOKEN);
        System.out.println(account.rate_limit_status());
    }

}
