package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity {
    private String username;
    private String displayName;
    private String description; // About me;;
    private String skills;
    private String education;
    private Gigs gigs;
    private LocalDateTime birth;
    private String know_language;
    private String location;
    private String balance;
}
