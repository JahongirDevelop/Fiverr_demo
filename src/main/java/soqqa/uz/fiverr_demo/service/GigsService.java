package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.GigsCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.GigsResponse;
import soqqa.uz.fiverr_demo.entity.Card;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.entity.enums.Category;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.GigsRepository;
import soqqa.uz.fiverr_demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static soqqa.uz.fiverr_demo.entity.enums.UserRole.SELLING;

@Service
@RequiredArgsConstructor
public class GigsService {
    private final UserRepository userRepository;
    private final GigsRepository gigsRepository;
    private final ModelMapper modelMapper;
    public GigsResponse create(GigsCreateRequest createRequest) {
        if (!userRepository.existsById(createRequest.getUser())){
            throw new DataNotFoundException("not found user with this id: " + createRequest.getUser());
        }
        Gigs gigs = modelMapper.map(createRequest, Gigs.class);
        Optional<User> byId = userRepository.findById(createRequest.getUser());

        List<Gigs> gig = new ArrayList<>();
        gig.add(gigs);
        byId.get().setGigs(gig);

        User save = modelMapper.map(byId, User.class);
        save.setUserRole(SELLING);
        userRepository.save(save);
        gigs.setUser(save);
        gigs.setCreatedDate(LocalDateTime.now());
        gigsRepository.save(gigs);
        return modelMapper.map(createRequest, GigsResponse.class);
    }

    public String delete(UUID gigsId){
        Gigs gigs = getGigs(gigsId);
        gigsRepository.deleteById(gigs.getId());
        return "Successfully deleted: " + gigs.getGigTitle();
    }

    public Gigs getGigs(UUID gigsId){
        return gigsRepository.findById(gigsId)
                .orElseThrow(() -> new DataNotFoundException("Gigs not found with this id: " + gigsId));
    }

    public List<GigsResponse> getAllGigsByCategory(int page, int size, Category category) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Gigs> gigsPage = gigsRepository.findAllByCategory(pageable, category);
        List<Gigs> gigs = gigsPage.getContent();

        List<GigsResponse> gigsResponses = new ArrayList<>();

        for (Gigs gig : gigs) {
            GigsResponse gigsResponse = GigsResponse.builder()
                    .user(gig.getUser().getId())
                    .gigTitle(gig.getGigTitle())
                    .category(gig.getCategory())
                    .full_name(gig.getFull_name())
                    .description(gig.getDescription())
                    .price(gig.getPrice())
                    .programmingLanguage(gig.getProgrammingLanguage())
                    .build();
            gigsResponses.add(gigsResponse);
        }
        return gigsResponses;
    }

    public List<GigsResponse> getAll() {
        List<GigsResponse> gigsResponses = new ArrayList<>();
        for (Gigs gigs : gigsRepository.findAll()) {
            gigsResponses.add(modelMapper.map(gigs, GigsResponse.class));
        }
        return gigsResponses;
    }

}
