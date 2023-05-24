package cinema.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tickets")
@Builder
@AllArgsConstructor
public class SoldTicketEntity {
    @Id
    String token;
    int price;
    @Column(name = "i_row")
    int row;
    @Column(name = "i_column")
    int column;
}
