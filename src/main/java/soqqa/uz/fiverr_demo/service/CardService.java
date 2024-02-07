package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.CardCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.CardResponse;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.exception.DataAlreadyExistsException;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.CardRepository;
import soqqa.uz.fiverr_demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CardResponse create(CardCreateRequest createRequest) {
        // Check if the card already exists
        if (cardRepository.existsByCardNumber(createRequest.getCardNumber())) {
            throw new DataAlreadyExistsException("Card already exists with: " + createRequest.getCardNumber());
        }

        // Retrieve the user by ID
        Optional<User> userOptional = userRepository.findById(createRequest.getOwnerId());

        // Check if the user exists
        if (userOptional.isEmpty()) {
            throw new DataNotFoundException("Owner not found with Id: " + createRequest.getOwnerId());
        }

        // Retrieve the user object from Optional
        User user = modelMapper.map(userOptional, User.class);

        // Create a new Card object from the request
        Card card = modelMapper.map(createRequest, Card.class);

        // Set the owner of the card
        card.setOwner(user);

        // Set the card for the user
        user.setCard(card);

        // Save the user (this will cascade the save operation to the card)
        userRepository.save(user);

        // Return the response
        return modelMapper.map(card, CardResponse.class);
    }

    public String delete(UUID cardId){
        Card card = getCard(cardId);
        cardRepository.deleteById(card.getId());
        return "Successfully deleted: " + card.getCardNumber();
    }

    public Card getCard(UUID cardId){
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new DataNotFoundException("Card not found with this id: " + cardId));
    }

    public List<CardResponse> getAll() {
        List<CardResponse> allResponse = new ArrayList<>();
        for (Card card : cardRepository.findAll()) {
            allResponse.add(modelMapper.map(card, CardResponse.class));
        }
        return allResponse;
    }
}
