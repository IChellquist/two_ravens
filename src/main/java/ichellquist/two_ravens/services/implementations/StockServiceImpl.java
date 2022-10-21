package ichellquist.two_ravens.services.implementations;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import ichellquist.two_ravens.domain.Stock;
import ichellquist.two_ravens.repositories.StockRepository;
import ichellquist.two_ravens.services.StockService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final WebBrowserServiceImpl webBrowserService;
    public StockServiceImpl(StockRepository stockRepository, WebBrowserServiceImpl webBrowserService){
        this.webBrowserService = webBrowserService;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getStocksFromPredefinedScan(URL url) throws IOException {
        //return arraylist
        ArrayList<Stock> stocks = new ArrayList<>();
        List<HtmlTableRow> rows = webBrowserService.getRowsFromTableInHtmlPage("sccDataTable", url);
        //parse the table, skipping first row because it has no stock data
        for (int i = 1; i < rows.size(); i++) {
            HtmlTableRow row = rows.get(i);
            Stock stock = new Stock(
                    row.getCell(2).asNormalizedText(),
                    row.getCell(1).asNormalizedText(),
                    row.getCell(3).asNormalizedText(),
                    row.getCell(4).asNormalizedText(),
                    row.getCell(5).asNormalizedText()
            );
            stocks.add(stock);
        }

        return stocks;
    }
}
