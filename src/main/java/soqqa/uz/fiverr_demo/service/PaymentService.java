package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.PaymentCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.PaymentResponse;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.Payment;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.CardRepository;
import soqqa.uz.fiverr_demo.repository.GigsRepository;
import soqqa.uz.fiverr_demo.repository.PaymentRepository;
import soqqa.uz.fiverr_demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final GigsRepository gigsRepository;
    private final ModelMapper modelMapper;
    private final String fiverr_card = "bac7f2d5-2e4e-4c94-a1f6-b67ceb965558";

    public PaymentResponse create(PaymentCreateRequest createRequest) {

        if(!gigsRepository.existsById(createRequest.getGigsId())){
            throw new DataNotFoundException("gis not found: " +  createRequest.getGigsId().toString());
        }
        Optional<Gigs> byId = gigsRepository.findById(createRequest.getGigsId());
        Gigs gigs = modelMapper.map(byId, Gigs.class);
        gigs.setPaymentCount(gigs.getPaymentCount() + 1);
        gigsRepository.save(gigs);

        User user = userRepository.findByGigs(byId);
        if(!cardRepository.existsByOwner(user)) {
            throw new DataNotFoundException("Seller Card not found: " + createRequest.getSellerCard().toString());
        } else if (!cardRepository.existsByCardNumber(createRequest.getBuyerCard())) {
            throw new DataNotFoundException("Buyer Card not found: " + createRequest.getBuyerCard().toString());
        }
        Card card = cardRepository.findByCardNumber(createRequest.getBuyerCard());

        Payment payment = modelMapper.map(createRequest, Payment.class);

        payment.setGigs(modelMapper.map(byId, Gigs.class));
        payment.setSellerCard(user.getCard());
        payment.setBuyerCard(card);

        Optional<Card> fiverrCard = cardRepository.findById(UUID.fromString(fiverr_card));

        Double amount = payment.getAmount();
        double fiverr = (amount * 20) / 100;
        payment.setAmount(fiverr);
        paymentRepository.save(payment);

        Card sellerCard = user.getCard();
        sellerCard.setBalance(sellerCard.getBalance() + fiverr);
        cardRepository.save(sellerCard);

        card.setBalance(card.getBalance() - payment.getAmount());
        cardRepository.save(card);

        fiverrCard.get().setBalance(fiverrCard.get().getBalance() + fiverr);
        cardRepository.save(modelMapper.map(fiverrCard, Card.class));

        return modelMapper.map(createRequest, PaymentResponse.class);
        }


    public String delete(UUID paymentId){
        Payment payment = getPayment(paymentId);
        paymentRepository.deleteById(payment.getId());
        return "Successfully deleted: ";
    }

    public Payment getPayment(UUID paymentId){
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new DataNotFoundException("Payment not found with this id: " + paymentId));
    }

    public List<PaymentResponse> getAll() {
        List<PaymentResponse> list = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            list.add(modelMapper.map(payment, PaymentResponse.class));
        }
        return list;
    }
    public List<Payment> findPaymentsBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return paymentRepository.findPaymentsBetween(startTime, endTime);
    }
}
