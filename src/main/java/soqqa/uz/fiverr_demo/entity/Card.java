package soqqa.uz.fiverr_demo.entity;
import jakarta.persistence.*;
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
    private Double balance;
    @OneToOne
    private User owner;


}
