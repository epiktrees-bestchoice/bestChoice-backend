package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Role;
import bestChoicebackend.spring.domain.User;
import bestChoicebackend.spring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트를 실행 -> transaction 실행 -> 롤백 -> 반영안됨
public class UserServiceTest {

    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        userService = new UserService(userRepository); // 외부에서 객체를 넣어주는것 -> DI
        // 온전한 하나의 저장소에대해 테스트하기 위함.
    }
    @Test
    void 회원가입() {
        // given
        User user = new User();
        user.setName("spring");
        user.setRole(Role.valueOf("USER"));
        user.setUserEmail("abc@gmail.com");

        // when
        String savedId = userService.join(user); // 이미 가입된 sping, test마다 독립성을 유지해야한다.
        // then
        User finduser = userService.findByUserEmail(savedId).get();
        assertThat(user.getName()).isEqualTo(finduser.getName());
    }

    @Test
    public void 중복_회원_예외() { //     하나의 test case
        // given
        User user1 = new User();
        user1.setName("spring");
        user1.setRole(Role.valueOf("USER"));
        user1.setUserEmail("abc@gmail.com");

        User user2 = new User();
        user2.setName("spring2");
        user2.setRole(Role.valueOf("USER"));
        user2.setUserEmail("abc@gmail.com");
        // when
        userService.join(user1); // 정상
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//       try, catch는 너무 복잡함
        try{
            userService.join(user2); // 예외 발생할 것
            fail("예외가 발생해야합니다");
        } catch (IllegalStateException e) {
            // 정상 작동
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

    @Test
    public void 회원_정보_수정(){
        User user1 = new User();
        user1.setName("spring");
        user1.setRole(Role.valueOf("USER"));
        user1.setUserEmail("abc@gmail.com");

        userService.join(user1);
        User updatedUser = userService.update(user1, user1.getName(), "springHellow", user1.getPhoneNumber(), user1.getPicture());
        User finduser = userService.findByUserEmail(updatedUser.getUserEmail()).get();
        assertThat(finduser.getNickName()).isEqualTo("springHellow");
    }
}
