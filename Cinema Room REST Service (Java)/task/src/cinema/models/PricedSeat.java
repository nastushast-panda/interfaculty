package cinema.models;

import lombok.Builder;

@Builder
public record PricedSeat(
        int price,
        int row,
        int column
) { }

