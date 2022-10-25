package ichellquist.two_ravens.services.implementations;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebBrowserServiceImplTest {

    WebBrowserServiceImpl webBrowserService;
    @Value("${gainer_table_url}")
    String table_url;
    @Value("${gainer_table_id}")
    String table_Id;
    @Value("${base_stockimage_url}")
    String base_image_url;
    @Value("${azure_url}")
    String azure_url;
    @Value("${azure_api_header}")
    String azure_api_header;
    @Value("${azure_api_key}")
    String azure_api_key;

    @BeforeEach
    void setUp() {webBrowserService = new WebBrowserServiceImpl();
    }

    @Test
    void getStockImage() throws IOException {
        BufferedImage stock_image = webBrowserService.getStockImage(base_image_url, "SPY" );
        //make sure the image is not null
        assertNotEquals(null, stock_image);
        //best test I could think of, get the image and get its height and its width
        int height = stock_image.getHeight();
        int width = stock_image.getWidth();
        assertEquals(700, width);
        assertEquals(530, height);
    }

    @Test
    void getAzureNewsArticles() throws URISyntaxException, IOException {
        List<JSONObject> rawNewsArticles = webBrowserService.getAzureNewsArticles(azure_url, azure_api_header, azure_api_key, "SPDR S&P 500 ETF");
        assertNotEquals(null, rawNewsArticles);
        assertTrue(rawNewsArticles.size() > 0);
    }

    @Test
    void getAndSetPage() {
    }

    @Test
    void getRowsFromTableInHtmlPage() throws IOException {
        URL url = new URL(table_url);
        //makes sure it retrieves table and there is more than one row
        assertNotEquals(0, webBrowserService.getRowsFromTableInHtmlPage(table_Id, url).size());
    }
}