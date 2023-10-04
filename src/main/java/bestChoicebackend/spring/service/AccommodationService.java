package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;

    /**
     * 숙소 상세
     * 숙소 ID로 찾아서 숙소 객체 반환
     */
    public Accommodation findById(Long accommodationId){
        Optional<Accommodation> accommodation =  accommodationRepository.findById(accommodationId);
        return accommodation.orElse(null);
    }

    /**
     * 전체 숙소 List 반환
     */
    public List<Accommodation> findAll(){
        List<AccommodationType> accommodationTypes = new ArrayList<AccommodationType>(List.of(AccommodationType.HOTEL, AccommodationType.MOTEL, AccommodationType.PENSION, AccommodationType.GUESTHOUSE, AccommodationType.CAMPING));
        List<String> regions = new ArrayList<String>(List.of("경기","서울","부산","제주","인천"));
        String baseImgUrl = "https://openreactsol.s3.ap-northeast-2.amazonaws.com/accommodations/";

        for (int i=0;i<10;i++){
            Accommodation accommodation = new Accommodation();
            accommodation.setAccommodationName(String.valueOf(i+1)+"번째 숙소");
            accommodation.setType(accommodationTypes.get(i % 5));
            accommodation.setRegion(regions.get(i % 5));
            accommodation.setPrice(Long.valueOf(i * 10000));
            accommodation.setIntroduce("hihi~");
            accommodation.setImgUrl("");
            accommodationRepository.save(accommodation);
        }
        return accommodationRepository.findAll();
    }


    public void deleteAll() {
        accommodationRepository.deleteAll();
    }
}
