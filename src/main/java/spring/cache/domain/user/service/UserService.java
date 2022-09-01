package spring.cache.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.cache.domain.user.User;
import spring.cache.domain.user.dto.SignupDto;
import spring.cache.domain.user.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void save(SignupDto signUpDto) {

        User user = User.builder()
                .id(signUpDto.getId())
                .name(signUpDto.getName())
                .build();

        userRepository.save(user);
    }

    public List<User> fineUsers(String id) {
        List<User> users = userRepository.findUsersById(id);

        System.out.println("User Select from DB : " + users.size());
        return users;
    }
}
