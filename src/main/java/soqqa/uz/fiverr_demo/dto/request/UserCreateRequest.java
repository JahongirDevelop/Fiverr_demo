package soqqa.uz.fiverr_demo.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String email;
    private String password;
    private String username;
    private String displayName;
    private String description; // About me;;
    private String skills;
    private String education;
    private LocalDateTime birth;
    private String know_language;
    private String location;

}
