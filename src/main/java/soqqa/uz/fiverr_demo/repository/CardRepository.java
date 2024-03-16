package soqqa.uz.fiverr_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.User;
import java.util.UUID;
@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByOwner(User owner);
    boolean existsByCardNumber(String buyerCard);

    Card findByCardNumber(String buyerCard);
}
