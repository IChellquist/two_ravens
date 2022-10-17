package ichellquist.two_ravens.repositories;
import ichellquist.two_ravens.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
