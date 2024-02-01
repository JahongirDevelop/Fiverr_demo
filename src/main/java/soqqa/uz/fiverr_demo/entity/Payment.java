package soqqa.uz.fiverr_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment extends BaseEntity {
    private Double amount;
    private String paymentMethod;
    private String currency;
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private Card buyerCard; // jonatuvchi
    @OneToOne
    @JoinColumn(name = "seller_id") // oluvchi
    private Card sellerCard;

    // In the Payment entity
    @ManyToOne
    @JoinColumn(name = "fiverr_id")
    private Fiverr fiverr;

}
