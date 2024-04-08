package JsoupConnection;

import Config.TelegramConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupConnection {
    public static void main(String[] args) throws IOException {
        TelegramConfig config = new TelegramConfig();
//        System.out.println(connectWith$(config.getUrl())); should change basing on users choice
    }
    private static String connectWith$(String url) throws IOException {
        for(Element el:Jsoup.connect(url).get().select("a")){
            System.out.println(el);
        }
        return url;
    }


}
