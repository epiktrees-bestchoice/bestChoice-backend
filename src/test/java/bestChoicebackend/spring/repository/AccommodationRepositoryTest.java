package bestChoicebackend.spring.repository;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class AccommodationRepositoryTest {
    @Autowired
    private AccommodationRepository accommodationRepository;

    // test 미통과

}
