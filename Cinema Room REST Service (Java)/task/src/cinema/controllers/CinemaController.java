package cinema.controllers;

import cinema.config.CinemaConfig;
import cinema.exceptions.OutOfBoundsCoordinatesException;
import cinema.exceptions.WrongPasswordException;
import cinema.models.CinemaStatistics;
import cinema.models.Seat;
import cinema.models.SoldTicket;
import cinema.models.request.TokenDTO;
import cinema.models.response.CinemaResponse;
import cinema.models.response.ReturnedTicket;
import cinema.services.CinemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class CinemaController {
    CinemaService cinemaService;
    CinemaConfig cinemaConfig;

    @GetMapping("/seats")
    public CinemaResponse seats() {
        return new CinemaResponse(
                cinemaConfig.totalRows(),
                cinemaConfig.totalColumns(),
                cinemaService.getAvailableSeats()
        );
    }

    @PostMapping("/purchase")
    public SoldTicket purchase(
            @RequestBody Seat seat
    ) throws OutOfBoundsCoordinatesException {
        log.info("Purchase request for seat = {}",
                seat);
        return cinemaService.purchase(seat);
    }

    @PostMapping("/return")
    ReturnedTicket returnHandler(@RequestBody TokenDTO tokenDTO) {
        return new ReturnedTicket(cinemaService.returnTicket(tokenDTO));
    }

    @PostMapping("/stats")
    CinemaStatistics stats(@RequestParam(required = false) Optional<String> password) {
        System.out.println(cinemaConfig.secret());
        password.filter(p -> password.orElse("").equals(cinemaConfig.secret()))
                .orElseThrow(WrongPasswordException::new);
        return cinemaService.stats();
    }

}
