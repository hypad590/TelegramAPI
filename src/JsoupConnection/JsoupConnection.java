package JsoupConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupConnection {
    public static void main(String[] args) {
        try {
            connectWithUrl("https://moto.av.by/bike");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectWithUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements titleElements = doc.select("span.catalog__title");

        if (titleElements.isEmpty()) {
            System.out.println("No title elements found for URL: " + url);
            return;
        }

        for (Element el : titleElements) {
            System.out.println(el.text());
        }
    }
}