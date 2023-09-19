package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Role;
import bestChoicebackend.spring.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){ // 실무에서는 테스트 케이스를 통과하지 않으면 다음 단계로 넘어가지 못하게 한다.
        User user = new User();
        user.setName("user1");
        user.setRole(Role.valueOf("USER"));
        userRepository.save(user); // 저장
        User resultId = userRepository.findById(user.getUserId()).get(); // 불러오기
        User resultName = userRepository.findByName(user.getName()).get(); // 불러오기
//        Assertions.assertEquals(member, null); // 기대값 : 실행값
//        org.opentest4j.AssertionFailedError:
//        Expected :com.example.hellospring.domain.Member@61009542
//        Actual   :null

        Assertions.assertThat(resultId).isEqualTo(user);
        Assertions.assertThat(resultName).isEqualTo(user);
    }

    @Test
    public void findByUserEmail(){
        User user1 = new User();
        user1.setName("spring1");
        user1.setRole(Role.valueOf("USER"));
        user1.setUserEmail("haxr369@gmail.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("spring2");
        user2.setRole(Role.valueOf("USER"));
        user2.setUserEmail("oohsol@naver.com");
        userRepository.save(user2);

        User result = userRepository.findByUserEmail("haxr369@gmail.com").get();

        Assertions.assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll(){
        User user1 = new User();
        user1.setName("spring1");
        user1.setRole(Role.valueOf("USER"));
        user1.setUserEmail("haxr369@gmail.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("spring2");
        user2.setRole(Role.valueOf("USER"));
        user2.setUserEmail("oohsol@naver.com");
        userRepository.save(user2);

        List<User> result = userRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void updateNickName(){
        User user1 = new User();
        user1.setName("spring1");
        user1.setRole(Role.valueOf("USER"));
        user1.setNickName("spring1");
        userRepository.save(user1);

        user1.update(user1.getName(), "helloWorld","https://gogogo.com");
        User result = userRepository.findByName(user1.getName()).get();
        Assertions.assertThat(result.getNickName()).isEqualTo("helloWorld");
    }

    @Test
    public void build(){
        User user1 = User.builder()
                .name("user1")
                .nickName("user1")
                .userEmail("dsfds.gamil.com")
                .picture("https://s3.gogogo.com")
                .role(Role.valueOf("USER"))
                .social("kakao")
                .build();
        userRepository.save(user1);
        User result = userRepository.findByName(user1.getName()).get();
        Assertions.assertThat(result).isEqualTo(user1);
    }
}
