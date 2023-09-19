package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){ // 실무에서는 테스트 케이스를 통과하지 않으면 다음 단계로 넘어가지 못하게 한다.
        User user = new User();
        user.setName("user1");
        userRepository.save(user); // 저장
        User result = userRepository.findById(user.getUserId()).get(); // 불러오기
//        Assertions.assertEquals(member, null); // 기대값 : 실행값
//        org.opentest4j.AssertionFailedError:
//        Expected :com.example.hellospring.domain.Member@61009542
//        Actual   :null

        Assertions.assertThat(result).isEqualTo(user);
    }
}
