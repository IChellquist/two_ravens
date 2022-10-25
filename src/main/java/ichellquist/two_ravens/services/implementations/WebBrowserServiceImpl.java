package ichellquist.two_ravens.services.implementations;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import ichellquist.two_ravens.services.WebBrowserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebBrowserServiceImpl implements WebBrowserService {

    private final WebClient client;
    private final HttpClient http_client;

    public WebBrowserServiceImpl (){
        this.client = new WebClient();
        http_client = HttpClients.createDefault();
    }


    @Override
    public BufferedImage getStockImage(String base_url, String ticker) throws IOException {
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlPage page = client.getPage(base_url + ticker);
        HtmlImage image = page.getHtmlElementById("chartImg");
        return image.getImageReader().read(0);

    }

    @Override
    public List<JSONObject> getAzureNewsArticles(String api_url, String api_header, String apiKey, String stock_name) throws URISyntaxException, IOException {

        //create a new URI for searching Bing News using the stock_name
        URIBuilder builder;
        URI uri;

        builder = new URIBuilder(api_url);
        builder.setParameter("q", stock_name);
        builder.setParameter("count", "10"); //The number of news articles returned
        builder.setParameter("offset", "0");
        builder.setParameter("mkt", "en-us"); //The language that will be returned
        builder.setParameter("safeSearch", "Moderate"); //Prevents my sea
        //Creates the URI from the previously declared constraints
         uri = builder.build();
         //Create an HTTP Get Request using the URI just built
        HttpGet get_request = new HttpGet(uri);
        //Add the subscription key to the header
        get_request.setHeader(api_header, apiKey);
        //get an HttpResponse
        HttpResponse response = http_client.execute(get_request);
        HttpEntity entity = response.getEntity();
        String results = EntityUtils.toString(entity);
        //turn the result into a JSONObject
        JSONObject obj = new JSONObject(results);
        //get the nested news articles
        JSONArray raw_articles = obj.getJSONArray("value");
        //add all raw news articles to list of JSON Objects
        List<JSONObject> news_articles = new ArrayList<>();
        for (int i = 0; i < raw_articles.length(); i++)
            news_articles.add(raw_articles.getJSONObject(i));

        return news_articles;
    }

    @Override
    public void getAndSetPage(URL url) {

    }

    @Override
    public List<HtmlTableRow> getRowsFromTableInHtmlPage(String tableId, URL url) throws IOException {
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlPage page = client.getPage(url);
        HtmlTable table = page.getHtmlElementById(tableId);
        return table.getRows();
    }
}
