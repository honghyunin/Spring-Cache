package spring.cache.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import spring.cache.domain.user.User;
import spring.cache.domain.user.dto.UserRequestDto;
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
    public void save(@RequestBody UserRequestDto signupDto) {
        userService.save(signupDto);
    }

    @GetMapping("/users/{id}")
    @Cacheable(key = "#id", cacheNames = "UserStorage", condition = "#id.length() < 3")
    public List<User> findAllUser(@PathVariable String id) {
        return userService.fineUsers(id);
    }

    @DeleteMapping("/user/{id}")
    @CacheEvict(key = "#id", cacheNames = "UserStorage")
    public void deleteOne(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    @Cacheable(key = "#id", cacheNames = "UserStorage")
    public User findUser(@PathVariable String id) {
        return userService.findUser(id);
    }

    @PutMapping("/update/user/{id}")
    @CachePut(key = "#id", cacheNames = "UserStorage")
    public User updateUser(@PathVariable String id, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }
}
