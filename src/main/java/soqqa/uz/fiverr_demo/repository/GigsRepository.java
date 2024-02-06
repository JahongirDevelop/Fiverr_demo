package soqqa.uz.fiverr_demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.entity.enums.Category;

import java.net.ContentHandler;
import java.util.UUID;

@Repository
public interface GigsRepository extends JpaRepository<Gigs, UUID> {


    User findByUser(User user);

    Page<Gigs> findAllByCategory(Pageable pageable, Category category);
}
