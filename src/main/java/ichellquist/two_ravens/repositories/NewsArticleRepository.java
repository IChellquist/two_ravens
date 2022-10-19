package ichellquist.two_ravens.repositories;

import ichellquist.two_ravens.domain.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
}
