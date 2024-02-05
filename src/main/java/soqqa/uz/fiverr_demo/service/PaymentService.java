package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.PaymentCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.PaymentResponse;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.Payment;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.CardRepository;
import soqqa.uz.fiverr_demo.repository.GigsRepository;
import soqqa.uz.fiverr_demo.repository.PaymentRepository;
import soqqa.uz.fiverr_demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final GigsRepository gigsRepository;
    private final ModelMapper modelMapper;

    public PaymentResponse create(PaymentCreateRequest createRequest) {
//        if(!gigsRepository.existsById(createRequest.getGigs().getId())){
//            throw new DataNotFoundException("gis not found: " +  createRequest.getGigs().toString());
//        }
//        User byUser = gigsRepository.findByUser(createRequest.getGigs().getUser());
//        if(!cardRepository.exitsByOwner(byUser)) {
//            throw new DataNotFoundException("Seller Card not found: " + createRequest.getSellerCard().toString());
//        } else if (!cardRepository.existsById(createRequest.getBuyerCard().getId())) {
//            throw new DataNotFoundException("Buyer Card not found: " + createRequest.getBuyerCard().toString());
//        }
//        Payment payment = modelMapper.map(createRequest, Payment.class);
//
//
//
//        }
        return null ;
}
}
