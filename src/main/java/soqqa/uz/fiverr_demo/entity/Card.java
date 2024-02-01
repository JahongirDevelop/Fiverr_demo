package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Card extends BaseEntity {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String cardDate;
    private String securityCode;

    @ManyToOne
    @JoinColumn(name = "payment_id") // Assuming "payment_id" is the foreign key column in the Card table
    private Payment payment;

}
