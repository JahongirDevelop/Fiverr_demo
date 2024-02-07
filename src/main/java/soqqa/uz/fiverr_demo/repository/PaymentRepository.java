package soqqa.uz.fiverr_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soqqa.uz.fiverr_demo.entity.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment searchByTimestampAfterAndTimestampBefore(LocalDateTime after, LocalDateTime before);
//    List<Payment> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT p FROM payment p WHERE p.timestamp BETWEEN :startTime AND :endTime")
    List<Payment> findPaymentsBetween(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<Payment> findAllByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);


}
