package com.TikiData.platform.User.Controller;

import com.TikiData.platform.User.DTO.*;
import com.TikiData.platform.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.registerUser(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/admin-create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createAsAdmin(@Valid @RequestBody AdminCreateUserDTO dto) {
        UserResponseDTO response = userService.createUserByAdmin(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AdminUpdateUserDTO dto) {
        UserResponseDTO response = userService.updateUser(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserResponseDTO> getMyProfile(@PathVariable String email) {
        UserResponseDTO response = userService.getOwnProfile(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile/{email}")
    public ResponseEntity<UserResponseDTO> updateMyAccount(@PathVariable String email, @Valid @RequestBody UserUpdateOwnDTO dto) {
        UserResponseDTO response = userService.updateOwnAccount(email, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/profile/{email}")
    public ResponseEntity<Void> deleteMyAccount(@PathVariable String email) {
        userService.deleteOwnAccount(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
