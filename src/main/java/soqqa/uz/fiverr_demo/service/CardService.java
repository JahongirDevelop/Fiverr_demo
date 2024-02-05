package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.CardCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.CardResponse;
import soqqa.uz.fiverr_demo.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponse create(CardCreateRequest createRequest) {
        return new CardResponse();
    }
}
