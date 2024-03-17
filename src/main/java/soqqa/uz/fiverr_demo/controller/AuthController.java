package soqqa.uz.fiverr_demo.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soqqa.uz.fiverr_demo.config.jwt.AuthDto;
import soqqa.uz.fiverr_demo.config.jwt.JwtResponse;
import soqqa.uz.fiverr_demo.dto.request.UserCreateRequest;
import soqqa.uz.fiverr_demo.service.UserService;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public JwtResponse signIn(@Valid @RequestBody AuthDto dto) {
        return userService.signIn(dto);
    }

    @PostMapping("/sign-up")
    public String auth(@Valid @RequestBody UserCreateRequest dto) {
        return userService.signUp(dto);
    }
}
