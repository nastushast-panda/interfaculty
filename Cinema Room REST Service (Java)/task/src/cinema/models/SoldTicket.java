package cinema.models;

import lombok.Data;
import lombok.Value;

@Value
public class SoldTicket {
    final String token;
    final PricedSeat ticket;
}
