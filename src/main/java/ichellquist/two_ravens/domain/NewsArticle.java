package ichellquist.two_ravens.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String title;
    @Lob
    private String url;

    @Lob
    private String text;


    @Temporal(TemporalType.DATE)
    private Date date;

    public NewsArticle (String title, String url, String text, Date date){
        this.title = title;
        this.url = url;
        this.text = text;
        this.date = date;
    }

    public NewsArticle(){};

}
