package cinema.models.response;

import cinema.models.PricedSeat;

public record ReturnedTicket(
        PricedSeat returnedTicket
) {
}
