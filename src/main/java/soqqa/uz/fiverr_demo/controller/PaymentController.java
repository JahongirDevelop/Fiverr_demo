package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.uz.fiverr_demo.dto.request.PaymentCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.PaymentResponse;
import soqqa.uz.fiverr_demo.service.PaymentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(createRequest));
    }
}
