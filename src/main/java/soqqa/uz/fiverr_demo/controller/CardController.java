package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soqqa.uz.fiverr_demo.dto.request.CardCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.CardResponse;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create-card")
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.create(createRequest));
    }

    @DeleteMapping("/delete-card{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable UUID cardId){
        return ResponseEntity.status(200).body(cardService.delete(cardId));
    }

    @GetMapping("/get-all")
    public List<CardResponse> getAll(){
        return cardService.getAll();
    }

}
