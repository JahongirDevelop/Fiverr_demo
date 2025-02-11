package soqqa.uz.fiverr_demo.dto.response;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.entity.enums.Category;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GigsResponse {
    private UUID user;
    private String gigTitle;
    private Category category;
    private String full_name;
    private String description;
    private Double price;
    private String programmingLanguage;
}
