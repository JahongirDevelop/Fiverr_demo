package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soqqa.uz.fiverr_demo.dto.request.GigsCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.GigsResponse;
import soqqa.uz.fiverr_demo.service.GigsService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/gigs")
public class GigsController {
    private final GigsService gigsService;

    @PostMapping("/create-gigs")
    public ResponseEntity<GigsResponse> createGigs(@RequestBody @Valid GigsCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(gigsService.create(createRequest));
    }

    @DeleteMapping("/delete-gigs{gigsId}")
    public ResponseEntity<String> deleteGigs(@PathVariable UUID gigsId){
        return ResponseEntity.status(200).body(gigsService.delete(gigsId));
    }
}
