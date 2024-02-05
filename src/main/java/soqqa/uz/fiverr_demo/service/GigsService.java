package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.dto.request.GigsCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.GigsResponse;
import soqqa.uz.fiverr_demo.entity.Gigs;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.GigsRepository;
import soqqa.uz.fiverr_demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
