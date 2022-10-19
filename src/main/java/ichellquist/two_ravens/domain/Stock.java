package ichellquist.two_ravens.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Stock {

    public Stock() {}
    public Stock(String name, String ticker, String exchange, String sector, String industry) {
        this.name = name;
        this.ticker = ticker;
        this.exchange = exchange;
        this.sector = sector;
        this.industry = industry;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ticker;
    private String exchange;
    private String sector;
    private String industry;

    @OneToMany
    private Set<StockReportPicture> pictures = new HashSet<>();

    @OneToMany
    private Set<NewsArticle> articles = new HashSet<>();


}
