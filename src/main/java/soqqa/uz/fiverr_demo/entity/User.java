package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;

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
    @OneToOne
    private Gigs gigs;

    private LocalDateTime birth;
    private String know_language;
    private String location;
    private String balance;
    @OneToOne
    private Card card;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
