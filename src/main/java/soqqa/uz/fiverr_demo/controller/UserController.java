package soqqa.uz.fiverr_demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soqqa.uz.fiverr_demo.dto.request.UserCreateRequest;
import soqqa.uz.fiverr_demo.dto.response.GigsResponse;
import soqqa.uz.fiverr_demo.dto.response.PaymentResponse;
import soqqa.uz.fiverr_demo.dto.response.UserResponse;
import soqqa.uz.fiverr_demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserCreateRequest userCreateDto, Principal principal){
        return ResponseEntity.ok(userService.updateProfile(userCreateDto, principal));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Principal principal){
        return ResponseEntity.ok(userService.me(principal));
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<String > delete(Principal principal){
        return ResponseEntity.status(200).body(userService.delete(principal));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody @Valid UserCreateRequest userCr) {
        return ResponseEntity.ok(userService.addAdmin(userCr));
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchByGigs(@RequestParam String keyWord) {
        return ResponseEntity.status(200).body(userService.searchByGigs(keyWord));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

}
