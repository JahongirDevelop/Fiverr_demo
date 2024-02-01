package soqqa.uz.fiverr_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "fiverr")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Fiverr extends BaseEntity  {
    private Double fiverrCard;

    @JsonIgnore
    @OneToMany(mappedBy = "fiverr", cascade = CascadeType.ALL)
    private List<Payment> payments;
}

