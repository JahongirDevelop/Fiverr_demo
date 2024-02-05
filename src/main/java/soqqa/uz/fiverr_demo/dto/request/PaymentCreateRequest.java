package soqqa.uz.fiverr_demo.dto.request;

import lombok.*;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.enums.Currency;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentCreateRequest {
    private Double amount;
    private String paymentMethod;
    private Currency currency;
    private LocalDateTime timestamp;
    private UUID gigsId;
    private String buyerCard;
    private String sellerCard;
}
