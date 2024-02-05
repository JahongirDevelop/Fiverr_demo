package soqqa.uz.fiverr_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.uz.fiverr_demo.entity.Gigs;
import java.util.UUID;

@Repository
public interface GigsRepository extends JpaRepository<Gigs, UUID> {
}