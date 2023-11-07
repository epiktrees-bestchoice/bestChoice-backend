package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.*;
import bestChoicebackend.spring.dto.SubImg.SubImgResDto;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.KeywordRepository;
import bestChoicebackend.spring.repository.MtypeRepository;
import bestChoicebackend.spring.repository.SubImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final SubImgRepository subImgRepository;
    /**
     * 숙소 상세
     * 숙소 ID로 찾아서 숙소 객체 반환
     */
//    public Accommodation findById(Long accommodationId){
//        Optional<Accommodation> accommodation =  accommodationRepository.findById(accommodationId);
//        return accommodation.orElse(null);
//    }
    public AccommodationResDto findById(Long accommodationId){
        Accommodation accommodation =  accommodationRepository.findByAccommodationId(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        List<SubImg> subImgs = subImgRepository.findByAccommodationId(accommodationId);
        List<String> subImgURLs = subImgs.stream()
                .map(SubImg::getSubImgUrl
                ) .toList();

        return AccommodationResDto.builder()
                .id(accommodation.getAccommodationId())
                .accommodationName(accommodation.getAccommodationName())
                .price(accommodation.getPrice())
                .type(accommodation.getType())
                .imgUrl(accommodation.getImgUrl())
                .introduce(accommodation.getIntroduce())
                .subImgUrls(subImgURLs).build();
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

    public String makeNameRandome(String accommodationType, String region){
        String randomName = getRandomName();
        // accommodationType과 region을 사용하여 이름을 만듭니다.
        String generatedName = randomName + " " + region + " " + accommodationType;
        // 이름의 총 길이가 15글자를 초과하지 않도록 합니다.
        if (generatedName.length() > 20) {
            generatedName = generatedName.substring(0, 15);
        }
        return generatedName;
    }

    private static String getRandomName() {
        List<String> names = new ArrayList<>(List.of("오션뷰","마운틴뷰","크리스탈","에메랄드","루비","메리야트",
                "바다뷰","산맥뷰","크리스탈","에메랄드","루비","마리엇","브르즈 알 아랍",
                "더 리츠칼튼","더 플라자","더 베네치안","아틀란티스 파라다이스 아일랜드",
                "숲속", "미소", "푸른", "시간", "바람", "하늘", "별", "작은", "휴식", "모닝", "아침",
                "해변", "풍경", "여유", "노을", "기차", "새소리", "고요", "포근", "따스", "햇살",
                "채광", "숲", "향기", "조용", "꿈", "야외", "강", "호수", "바다", "산", "포 시즌스"
        ));
        // 무작위로 호텔 이름을 선택합니다.
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
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
                    accommodation.setAccommodationName(makeNameRandome(nowAccomodationType.toString(), nowRegion));
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
