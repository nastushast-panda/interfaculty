package cinema.mappers;

import cinema.models.PricedSeat;
import cinema.models.Seat;
import cinema.models.entity.SoldTicketEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Seat toSeat(PricedSeat pricedSeat) {
        return new Seat(pricedSeat.row(), pricedSeat.column());
    }

    public PricedSeat toPricedSeat(SoldTicketEntity soldTicketEntity) {
        return new PricedSeat(
                soldTicketEntity.getPrice(),
                soldTicketEntity.getRow(),
                soldTicketEntity.getColumn()
        );
    }
}
