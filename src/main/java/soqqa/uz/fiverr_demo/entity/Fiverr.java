package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "fiverr")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Fiverr extends BaseEntity  {
    private Card buyerCard;
    private Card sellerCard;

    private Payment payment;
}
