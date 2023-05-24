package cinema.config;

import cinema.repositories.SeatRepository;
import cinema.models.Seat;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SeatInitialization  {
    SeatRepository seatRepository;
    CinemaConfig cinemaConfig;

    @PostConstruct
    public void init() {
        for (int row = 1; row <= cinemaConfig.totalRows(); row++) {
            for (int col = 1; col <= cinemaConfig.totalColumns(); col++) {
                seatRepository.save(new Seat(
                        row,
                        col));
            }
        }
    }
}
