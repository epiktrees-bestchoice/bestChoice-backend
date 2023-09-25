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
    @Test
    public void findAll(){
        List<AccommodationType> accommodationTypes = new ArrayList<AccommodationType>(List.of(AccommodationType.HOTEL, AccommodationType.MOTEL, AccommodationType.PENSION, AccommodationType.GUESTHOUSE, AccommodationType.CAMPING));
        List<String> regions = new ArrayList<String>(List.of("경기","서울","부산","제주","인천"));
        for (int i=0;i<10;i++){
            Accommodation accommodation = new Accommodation();
            accommodation.setAccommodationName(String.valueOf(i)+"번째 숙소");
            accommodation.setType(accommodationTypes.get(i % 5));
            accommodation.setRegion(regions.get(i % 5));
            accommodation.setPrice(Long.valueOf(i * 10000));
            accommodation.setIntroduce("hihi~");
            accommodationRepository.save(accommodation);
        }
        List<Accommodation> accommodations = accommodationRepository.findAll();
        Assertions.assertThat(accommodations.size()).isEqualTo(10);
    }
}
