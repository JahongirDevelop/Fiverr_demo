package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
}
