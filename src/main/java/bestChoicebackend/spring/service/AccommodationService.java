package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Keyword;
import bestChoicebackend.spring.domain.Mtype;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.KeywordRepository;
import bestChoicebackend.spring.repository.MtypeRepository;
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

    public Accommodation addAccommodation(Accommodation accommodation){
        return accommodationRepository.save(accommodation);
    }

    /**
     * 숙소 상세
     * 숙소 ID로 찾아서 숙소 객체 반환
     */
    public Accommodation findById(Long accommodationId){
        Optional<Accommodation> accommodation =  accommodationRepository.findById(accommodationId);
        return accommodation.orElse(null);
    }

    public List<Accommodation> findByAccommodationType(String accommodationType){
        return accommodationRepository.findAllByTypeAndRegion(AccommodationType.valueOf(accommodationType), "서울");
    }

    /**
     * 전체 숙소 List 반환
     */
    public List<Accommodation> findAll(){
        List<AccommodationType> accommodationTypes = new ArrayList<AccommodationType>(List.of(AccommodationType.HOTEL, AccommodationType.MOTEL, AccommodationType.PENSION, AccommodationType.GUESTHOUSE, AccommodationType.CAMPING));
        List<String> regions = new ArrayList<String>(List.of("경기","서울","부산","제주","인천"));
        for (int i=0;i<10;i++){
            Accommodation accommodation = new Accommodation();
            accommodation.setAccommodationName(String.valueOf(i+1)+"번째 숙소");
            accommodation.setType(accommodationTypes.get(i % 5));
            accommodation.setRegion(regions.get(i % 5));
            accommodation.setPrice(Long.valueOf(i * 10000));
            accommodation.setIntroduce("hihi~");
            accommodationRepository.save(accommodation);
        }
        return accommodationRepository.findAll();
    }

    public List<Accommodation> createInit(){
        List<AccommodationType> accommodationTypes = new ArrayList<>(List.of(AccommodationType.HOTEL, AccommodationType.MOTEL, AccommodationType.PENSION, AccommodationType.GUESTHOUSE, AccommodationType.CAMPING));
        List<String> regions = new ArrayList<String>(List.of("경기","서울","부산","제주","인천"));
        String baseImgUrl = "https://d3dp03fmze904.cloudfront.net/accommodations/";

        // 5(type) x 5(region) x 4(images) = 100
        for(int i=0; i<5; i++){
            AccommodationType nowAccomodationType = accommodationTypes.get(i);
            for(int j=0; j<5; j++){
                String nowRegion = regions.get(j);
                for(int k=0; k<4; k++){
                    Accommodation accommodation = new Accommodation();
                    accommodation.setAccommodationName(String.valueOf(i+j+k+1)+"번째 숙소");
                    accommodation.setType(nowAccomodationType);
                    accommodation.setRegion(nowRegion);
                    accommodation.setPrice((long) ((k + 1) * 10000));
                    accommodation.setIntroduce("hihi~ intro of accommodation "+nowAccomodationType+"의 "+nowRegion+"의 숙소");
                    accommodation.setImgUrl(baseImgUrl + String.valueOf(nowAccomodationType)+ String.valueOf(k+1) +".jpg");
                    accommodationRepository.save(accommodation);
                }
            }
        }
        return accommodationRepository.findAll();
    }
    public void deleteAll() {
        accommodationRepository.deleteAll();
    }
}
