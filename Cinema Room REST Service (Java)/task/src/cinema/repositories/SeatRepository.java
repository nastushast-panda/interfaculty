package cinema.repositories;

import cinema.models.Seat;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SeatRepository {
    List<Seat> seats = new ArrayList<>();

    public void save(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getAvailableSeats() {
        return Collections.unmodifiableList(seats);
    }

    public boolean isAvailable(Seat seat) {
        return seats.contains(seat);
    }

    public void remove(Seat seat){
        seats.remove(seat);
    }

    public int count() {
        return seats.size();
    }
}