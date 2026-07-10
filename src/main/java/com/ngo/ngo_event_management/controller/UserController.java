package com.ngo.ngo_event_management.controller;

import com.ngo.ngo_event_management.entity.User;
import com.ngo.ngo_event_management.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
   @PutMapping("/{id}")
public User updateUser(
        @PathVariable Integer id,
        @RequestBody User updatedUser) {

    User user =
            userRepository.findById(id)
            .orElseThrow();

    user.setFullName(updatedUser.getFullName());
    user.setPhone(updatedUser.getPhone());
    user.setAddress(updatedUser.getAddress());

    return userRepository.save(user);
}
}