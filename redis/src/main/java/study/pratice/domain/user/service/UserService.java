package study.pratice.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.pratice.domain.user.dto.UserRequestDto;
import study.pratice.domain.user.repository.UserRepository;
import study.pratice.domain.user.User;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> userSave(UserRequestDto userRequestDto) {
        userRepository.save(
                User.builder()
                        .id(userRequestDto.getId())
                        .name(userRequestDto.getName())
                        .build());

        return userRepository.findAll();
    }

    public List<User> findAll() {
        log.info("Call by findAll ");
        return userRepository.findAll();
    }
}
