package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getByTimestampBetween")
    public ResponseEntity<List<PaymentResponse>> findAllByTimestampBetween(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<PaymentResponse> payments = paymentService.findAllByTimestampBetween(startTime, endTime);
        return ResponseEntity.ok(payments);
    }   // 2024-02-05T14:08:32.436000 ushbu formatda berislishi kerak timelar


}
