package soqqa.uz.fiverr_demo.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.enums.Currency;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentResponse {
    private Double amount;
    private String paymentMethod;
    private Currency currency;
    private LocalDateTime timestamp;
    private Gigs gigs;
    private Card buyerCard;
    private Card sellerCard;
}
