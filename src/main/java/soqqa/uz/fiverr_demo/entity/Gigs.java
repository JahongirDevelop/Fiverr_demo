package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
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
    private Category category;
    private String full_name;
    private String description;
    private Double price;
    private String programmingLanguage;
}
