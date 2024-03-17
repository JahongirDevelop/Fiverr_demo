package soqqa.uz.fiverr_demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import soqqa.uz.fiverr_demo.config.jwt.AuthDto;
import soqqa.uz.fiverr_demo.config.jwt.JwtResponse;
import soqqa.uz.fiverr_demo.config.jwt.JwtService;
import soqqa.uz.fiverr_demo.dto.request.UserCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.UserResponse;
import soqqa.uz.fiverr_demo.entity.User;
import soqqa.uz.fiverr_demo.entity.enums.UserRole;
import soqqa.uz.fiverr_demo.exception.DataNotFoundException;
import soqqa.uz.fiverr_demo.repository.UserRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import static soqqa.uz.fiverr_demo.entity.enums.UserRole.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public JwtResponse signIn(AuthDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (dto.getPassword().equals(user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }

    public String signUp(UserCreateRequest dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("this email is already exists: " + dto.getEmail());
        }

        User userEntity = modelMapper.map(dto, User.class);

        if (userEntity.getGigs() != null) {
            userEntity.setUserRole(SELLING);
        } else {
            userEntity.setUserRole(BUYING);
        }
            userEntity.setCreatedDate(LocalDateTime.now());
            userRepository.save(userEntity);
            return "Successfully sign up";
    }


    public  <T> T me(Principal principal) {
        User userEntity = userRepository.findById(UUID.fromString(principal.getName()))
                .orElseThrow(() -> new DataNotFoundException("User not found!"));;
        UserRole role = userEntity.getUserRole();
        if (role == ADMIN){
            UserResponse adminResponse = modelMapper.map(userEntity, UserResponse.class);
            adminResponse.setRole(ADMIN);
            return (T) adminResponse;
        } else if (role == USER) {
            UserResponse userResponse =  modelMapper.map(userEntity,UserResponse.class);
            userResponse.setRole(USER);
            return (T)userResponse;
        }
        return (T) modelMapper.map(userEntity,UserResponse.class);
    }

    public UserResponse updateProfile(UserCreateRequest user, Principal principal) {
        User entity = userRepository.findById(UUID.fromString(principal.getName())).
                orElseThrow(() -> new DataNotFoundException("User not found!"));
        if(!Objects.equals(user.getDisplayName(),null)){
            entity.setDisplayName(user.getDisplayName());
        }if(!Objects.equals(user.getPassword(),null)){
            entity.setPassword(user.getPassword());
        }if(!Objects.equals(user.getEmail(),null)){
            entity.setEmail(user.getEmail());
        }
        User userEntity = userRepository.save(entity);
        return modelMapper.map(userEntity,UserResponse.class);
    }

    public String delete(Principal principal) {
        userRepository.deleteById(UUID.fromString(principal.getName()));
        return "Deleted!";
    }
    public UserResponse addAdmin(UserCreateRequest userCr) {
        if (!userRepository.existsByEmail(userCr.getEmail())) {
            throw new DataNotFoundException("User not found");
        }
        User userEntity = modelMapper.map(userCr, User.class);
        userEntity.setUserRole(ADMIN);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);
    }

    public List<UserResponse> searchByGigs(String keyWord) {
        List<User> userEntities = userRepository.searchAllByGigsGigTitleContainingIgnoreCase(keyWord);
        return userEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class))
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAll() {
        List<UserResponse> arrayList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            arrayList.add(modelMapper.map(user, UserResponse.class));
        }
        return arrayList;
    }
}
