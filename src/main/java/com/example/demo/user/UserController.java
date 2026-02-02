package com.example.demo.user;

import com.example.demo.user.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest request, @PathVariable Long id) {
        return userService.updateUser(request, id);
    }

    @PatchMapping("/{id}")
    public UserResponse patchUser(@Valid @RequestBody PatchUserRequest request, @PathVariable Long id) {
        return userService.patchUser(request, id);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/count")
    public CountResponse count() {
        return userService.count();
    }

    @GetMapping("/by-mail")
    public UserResponse findByMail(@RequestParam String mail) {
        return userService.findByMail(mail);
    }

}
