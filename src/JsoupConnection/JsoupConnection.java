package JsoupConnection;

import Config.TelegramConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupConnection {
    public static void main(String[] args) throws IOException {
        TelegramConfig config = new TelegramConfig();
        config.setBikesCategory(config.getUrl());
        config.setCarsCategory(config.getUrl());
        System.out.println(connectWith$(config.getCategory()));
    }
    private static String connectWith$(String url) throws IOException {
        //if inlineQuery equals moto -> connect to moto.av.by -> get tag list / cars -> car tag list(based on category)
        //bot.execute(result.toString()); sip red talk to the devil
        for(Element el:Jsoup.connect(url).get().select("a")){
            System.out.println(el);
        }
        return url;
    }


}
