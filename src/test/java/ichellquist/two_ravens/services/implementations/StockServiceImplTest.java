package ichellquist.two_ravens.services.implementations;

import ichellquist.two_ravens.domain.Stock;
import ichellquist.two_ravens.repositories.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceImplTest {

    //url for the report, using a local file for the sake of this test.
    private String report_url = "/testing_resources/testdata_getStocksFromPredefinedScan.html";
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private WebBrowserServiceImpl webBrowserService = new WebBrowserServiceImpl();
    private final StockServiceImpl stockService = new StockServiceImpl(stockRepository, webBrowserService);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getStocksFromPredefinedScan() throws IOException {
        Resource resource = new ClassPathResource(report_url);
        List<Stock> stocks = stockService.getStocksFromPredefinedScan(resource.getURL());
        //size should be 111 stocks
        Stock stock = stocks.get(0);
        //111 stocks in the example HTML table
        assertEquals(111, stocks.size());
        //make sure the fields match from the table
        assertEquals("ACOR", stocks.get(0).getTicker());
        assertEquals("Acorda Therapeutics Inc.", stocks.get(0).getName());
        assertEquals("NASD", stocks.get(0).getExchange());
        assertEquals("Health Care", stocks.get(0).getSector());
        assertEquals("Biotechnology", stocks.get(0).getIndustry());
    }


}