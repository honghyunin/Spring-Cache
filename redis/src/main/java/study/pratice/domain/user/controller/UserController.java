package study.pratice.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import study.pratice.domain.user.dto.UserRequestDto;
import study.pratice.domain.user.service.UserService;
import study.pratice.domain.user.User;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @ResponseBody
    public List<User> save(@RequestBody UserRequestDto userRequestDto) {
        return userService.userSave(userRequestDto);
    }

    @GetMapping("/cache/{id}")
    @ResponseBody
    @Cacheable(key = "#id", cacheNames = "findId", value = "findId")
    public List<User> cacheFindAll(@PathVariable String id) {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<User> findAll(@PathVariable String id) {
        return userService.findAll();
    }
}
