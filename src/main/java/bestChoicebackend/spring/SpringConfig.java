package bestChoicebackend.spring;

import bestChoicebackend.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;
    @Autowired
    public SpringConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
