package spring.cache.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import spring.cache.domain.user.User;
import spring.cache.domain.user.dto.UserRequestDto;
import spring.cache.domain.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void save(UserRequestDto signUpDto) {

        User user = User.builder()
                .id(signUpDto.getId())
                .name(signUpDto.getName())
                .build();

        userRepository.save(user);
    }

    public List<User> fineUsers(String id) {
        List<User> users = userRepository.findUsersById(id)
                        .orElseThrow();

        userListNullCheck(users);

        log.info("User Select from DB : " + users.size());

        return users;
    }

    public User findUser(String id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new NullPointerException("Not Exists User"));

        log.info("User Select from DB : " + user.getId());

        return user;
    }

    @Transactional
    public User updateUser(String id, UserRequestDto userRequestDto) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new NullPointerException("Not Exists User"));

        user.update(userRequestDto);

        return user;
    }

    public void deleteUser(String id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new NullPointerException("Not Exists User"));

        log.info("User delete from cache : " + id);

//        userRepository.delete(user);
    }

    private void userListNullCheck(List<User> users) {
        if(users.isEmpty()) {
            throw new NullPointerException("Not Exists User");
        }
    }
}
