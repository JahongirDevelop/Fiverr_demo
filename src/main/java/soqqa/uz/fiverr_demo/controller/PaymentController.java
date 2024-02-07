package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soqqa.uz.fiverr_demo.dto.request.PaymentCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.PaymentResponse;
import soqqa.uz.fiverr_demo.entity.Payment;
import soqqa.uz.fiverr_demo.service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(createRequest));
    }

    @DeleteMapping("/delete-payment{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable UUID paymentId){
        return ResponseEntity.status(200).body(paymentService.delete(paymentId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public List<PaymentResponse> getAll(){
        return paymentService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-payment/{id}")
    public Payment getPaymentById(@PathVariable UUID id) {
        return paymentService.getPayment(id);
    }

    @GetMapping("/searchByTimestamp")
    public ResponseEntity<List<Payment>> getPaymentsBetween(
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime) {

        List<Payment> payments = paymentService.findPaymentsBetween(startTime, endTime);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

}
