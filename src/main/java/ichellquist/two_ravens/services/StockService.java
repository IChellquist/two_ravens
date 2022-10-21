package ichellquist.two_ravens.services;

import ichellquist.two_ravens.domain.Stock;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public interface StockService {
    List<Stock> getStocksFromPredefinedScan(URL url) throws IOException;
}
