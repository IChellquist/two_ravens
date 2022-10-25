package ichellquist.two_ravens.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

//this class exists mainly to prevent multiple WebClients from being created repeatedly with each method call
public interface WebBrowserService {


    BufferedImage getStockImage(String base_url, String ticker) throws IOException;
    //Performs a Microsoft Azure news search and returns JSON objects of the articles
    List<JSONObject> getAzureNewsArticles(String api_url, String api_header, String apiKey, String stock_name) throws URISyntaxException, IOException;
    //gets a page from a website and sets it within the service
    void getAndSetPage(URL url);
    //gets rows from an HTML table
    List<HtmlTableRow> getRowsFromTableInHtmlPage(String tableId, URL url) throws IOException;


}
