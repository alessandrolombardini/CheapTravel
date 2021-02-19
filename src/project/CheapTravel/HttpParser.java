package project.CheapTravel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpParser {

    public HttpParser() { }
    
    public List<String> getPrice(HttpEntity entity) throws UnsupportedOperationException, IOException{
        List<String> prices = new ArrayList<>();
        Document doc = Jsoup.parse(EntityUtils.toString(entity));
        for(Element elem: doc.getElementsByClass("num").not(".visible-sm-inline")) {
            prices.add(elem.text());
        }
        System.out.println(prices.toString());
        return prices;
    }

}
