package ichellquist.two_ravens.services.implementations;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import ichellquist.two_ravens.services.WebBrowserService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class WebBrowserServiceImpl implements WebBrowserService {

    private final WebClient client;

    public WebBrowserServiceImpl (){
        this.client = new WebClient();
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
    public List<JSONObject> getAzureNewsArticles(URL url, String apiName, String apiKey) {
        return null;
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
