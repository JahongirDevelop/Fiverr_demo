package soqqa.uz.fiverr_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.enums.Currency;

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
    @Enumerated(EnumType.STRING)
    private Currency currency;
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



}
