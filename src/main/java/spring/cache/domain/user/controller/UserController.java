package spring.cache.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import spring.cache.domain.user.User;
import spring.cache.domain.user.dto.SignupDto;
import spring.cache.domain.user.repository.UserRepository;
import spring.cache.domain.user.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    public void save(@RequestBody SignupDto signupDto) {
        userService.save(signupDto);
    }

    @GetMapping("/user/{id}")
    @Cacheable(key = "#id", cacheNames = "User")
    public List<User> findAllUser(@PathVariable String id) {
        return userService.fineUsers(id);
    }
}
