package soqqa.uz.fiverr_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity implements UserDetails {
    private String email;
    private String password;
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities =
                new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_" + userRole.name())));
        return authorities;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
