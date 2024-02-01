package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment extends BaseEntity {
    private Double amount;
    private Card buyerCard;
    private Card sellerCard;
}
