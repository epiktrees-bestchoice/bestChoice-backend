package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.User;
import bestChoicebackend.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){ // 외부에서 userRepository를 넣어주도록 함
        this.userRepository = userRepository;
    }

    // 유저 생성
    public String join (User user){
        // 중복된 이메일을 가진 user는 존재할 수 없다.
        // 이미 존재하는 유저가 다시 가입하려면 Exception 발생
        vaildateDuplicateMember(user);
        userRepository.save(user);
        return user.getUserEmail();
    }

    private void vaildateDuplicateMember(User user) {
        userRepository.findByUserEmail(user.getUserEmail())
                .ifPresent(u -> { // Optional의 메소드, 값이 존재하면 콜백함수 실행
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<User> findMembers(){
        return userRepository.findAll();
    }
    // 개별 회원 조회
    public Optional<User> findByUserId(Long userId) {return userRepository.findById(userId);}
    public Optional<User> findByUserEmail(String userEmail) {return userRepository.findByUserEmail(userEmail);}
    public Optional<User> findByName(String name) {return userRepository.findByName(name);}

    // 회원 정보 업데이트
    public String update(User user, String name, String nickName, String picture){
        // entity를 update하면 jpa가 자동으로 Hibernate update를 수행
        user.update(name, nickName, picture);
        return user.getUserEmail();
    }


}
