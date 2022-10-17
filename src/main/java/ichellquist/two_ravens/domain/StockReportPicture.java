package ichellquist.two_ravens.domain;

import lombok.*;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class StockReportPicture {


    public StockReportPicture(){}
    public StockReportPicture(Date date, BufferedImage image) throws IOException {
        this.date = date;

        //converts buffered image to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        this.image = baos.toByteArray();
    }
    @Enumerated(value = EnumType.STRING)
    private ReportType reportType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Lob
    private byte[] image;

    @OneToMany
    private Set<NewsArticle> articles = new HashSet<>();


}

