package cinema.models.response;

import cinema.models.PricedSeat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CinemaResponse (
        int totalRows,
        int totalColumns,
        List<PricedSeat> availableSeats
) {}
