package cinema.repositories;

import cinema.models.entity.SoldTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldTicketEntityRepository extends JpaRepository<SoldTicketEntity, String> {
    @Query("SELECT SUM(s.price) FROM SoldTicketEntity s")
    int sumPrices();
}
