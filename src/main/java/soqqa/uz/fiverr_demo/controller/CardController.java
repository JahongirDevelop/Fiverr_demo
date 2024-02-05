package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.uz.fiverr_demo.dto.request.CardCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.CardResponse;
import soqqa.uz.fiverr_demo.service.CardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create-card")
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.create(createRequest));
    }
}
