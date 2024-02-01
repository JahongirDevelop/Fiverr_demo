package soqqa.uz.fiverr_demo.dto.response;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {
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
    private UserRole role;
}
