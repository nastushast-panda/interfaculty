package cinema.services;

import cinema.config.CinemaConfig;
import cinema.exceptions.AlreadyPurchasedException;
import cinema.exceptions.OutOfBoundsCoordinatesException;
import cinema.exceptions.WrongTokenException;
import cinema.mappers.Mapper;
import cinema.models.CinemaStatistics;
import cinema.models.PricedSeat;
import cinema.models.Seat;
import cinema.models.SoldTicket;
import cinema.models.request.TokenDTO;
import cinema.repositories.SeatRepository;
import cinema.repositories.SoldTicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class CinemaService {
    SeatRepository seatRepository;
    CinemaConfig cinemaConfig;
    SoldTicketRepository soldTicketRepository;
    Mapper mapper;
    public List<PricedSeat> getAvailableSeats() {
        return seatRepository.getAvailableSeats()
                .stream()
                .map(this::addPrice)
                .toList();
    }

    private PricedSeat addPrice(Seat seat) {
        return PricedSeat.builder()
                .price(calcPrice(seat))
                .column(seat.column())
                .row(seat.row())
                .build();
    }

    private int calcPrice(Seat seat) {
        return seat.row() <= cinemaConfig.numberOfFirstRows()
                ? cinemaConfig.vipSeatPrice()
                : cinemaConfig.defaultSeatPrice();
    }

    public SoldTicket purchase(Seat seat) {
        if(illegalBounds(seat)) {
            throw new OutOfBoundsCoordinatesException();
        } else if (!seatRepository.isAvailable(seat)) {
            throw new AlreadyPurchasedException();
        } else {
            seatRepository.remove(seat);
            PricedSeat ticket = addPrice(seat);
            return soldTicketRepository.save(ticket);
        }
    }

    private boolean illegalBounds(Seat seat) {
        return seat.column() > cinemaConfig.totalColumns()
                || seat.row() > cinemaConfig.totalRows()
                || seat.row() < 0
                || seat.column() < 0;
    }

    public PricedSeat returnTicket(TokenDTO tokenDTO) {
        var pricedSeat = soldTicketRepository.remove(tokenDTO.token())
                .orElseThrow(WrongTokenException::new);
        seatRepository.save(mapper.toSeat(pricedSeat));
        return pricedSeat;
    }

    public CinemaStatistics stats() {
        return CinemaStatistics.builder()
                .numberOfPurchasedTickets(soldTicketRepository.count())
                .numberOfAvailableSeats(seatRepository.count())
                .currentIncome(soldTicketRepository.totalIncome())
                .build();
    }
}
