package cinema.repositories;

import cinema.mappers.Mapper;
import cinema.models.PricedSeat;
import cinema.models.SoldTicket;
import cinema.models.entity.SoldTicketEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SoldTicketRepository {
    private final SoldTicketEntityRepository repository;
    private final Mapper mapper;

    public SoldTicket save(PricedSeat ticket) {
        String token = UUID.randomUUID().toString();
        SoldTicket res = new SoldTicket(
                token, ticket
        );

        SoldTicketEntity soldTicketEntity = SoldTicketEntity.builder()
                .row(ticket.row())
                .column(ticket.column())
                .price(ticket.price())
                .token(token)
                .build();

        repository.save(soldTicketEntity);
//        data.put(token, ticket);
        return res;
    }

    public Optional<PricedSeat> remove(String token) {
        log.info("remove token = '{}'", token);
        var entity = repository.findById(token);
        entity.ifPresent(repository::delete);
        return entity.map(mapper::toPricedSeat);
    }

    public int totalIncome() {
        return repository.sumPrices();
    }

    public int count() {
        return (int) repository.count();
    }
}
