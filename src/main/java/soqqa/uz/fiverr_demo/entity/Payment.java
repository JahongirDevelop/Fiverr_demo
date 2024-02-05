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

    @ManyToOne
    @JoinColumn(name = "gigs_id")
    private Gigs gigs;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Card buyerCard; // Card used by the buyer for payment

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Card sellerCard; // Card of the seller receiving payment

    @ManyToOne
    @JoinColumn(name = "fiverr_id")
    private Fiverr fiverr;

}
