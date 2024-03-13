package soqqa.uz.fiverr_demo.entity;
import jakarta.persistence.*;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.enums.Category;
@Entity(name = "gigs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Gigs extends BaseEntity {

    private String gigTitle;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    private String full_name;
    private String description;
    private Double price;
    private String programmingLanguage;
    private Integer paymentCount;
}
