package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Region;
import bestChoicebackend.spring.domain.Reserve;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.ReserveRepository;
import com.nimbusds.oauth2.sdk.util.ListUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final AccommodationRepository accommodationRepository;
    private final ReserveRepository reserveRepository;


    public Page<Accommodation> findByAccommodationName(String text, Pageable pageable){
        Page<Accommodation> accommodations = accommodationRepository.findByAccommodationNameContaining(text, pageable);
        return accommodations;
    }

    /**
     * MOTEL 검색
     */
    public Page<Accommodation> findMotelByCondition(Region region, String sortVal, LocalDate sel_date, LocalDate sel_date2, Long minPrice, Long maxPrice, Long reserve, String keyword, Pageable pageable){
        if(sel_date==null || sel_date2==null){
            sel_date = LocalDate.now();
            sel_date2=LocalDate.now().plusDays(1);
        }
        return accommodationRepository.findByAccommodationTypeAndRegion(AccommodationType.MOTEL, region, pageable);
    }


    /**
     * HOTEL
     */
    public Page<Accommodation> findHotelByCondition(Region region, String sortVal, LocalDate sel_date, LocalDate sel_date2, Long minPrice, Long maxPrice, Long reserve, String keyword, Pageable pageable) {
//        return accommodationRepository.findByAccommodationTypeAndRegion(AccommodationType.HOTEL, region, pageable);
        if (reserve == 0) {
            return accommodationRepository.findByAccommodationTypeAndRegion(AccommodationType.HOTEL, region, pageable);
        }
        // 예약 가능한 숙소만을 요청한 경우 reserve=1
        else {
            List<Accommodation> reservedAccommodation = new ArrayList<>();
            // 1. 예약을 원하는 날짜에 예약 정보가 존재하는 숙소 객체들을 중복없도록 가져온다.
            for (int i = 0; i < Period.between(sel_date, sel_date2).getDays() + 1; i++) {
                List<Reserve> reserves = reserveRepository.findReservedAccommodation(AccommodationType.HOTEL, region, java.sql.Date.valueOf(sel_date.plusDays(i)));
                for (int j = 0; j < reserves.size(); j++) {
                    // 예약된 숙소를 가져온다.
                    Accommodation accommodation = reserves.get(j).getAccommodationId();
                    if (!reservedAccommodation.contains(accommodation)) {
                        reservedAccommodation.add(accommodation);
                    }
                }
            }

            // 2. 모든 숙소에서 1의 숙소들을 제외한다.
            List<Accommodation> availableAccommodation = accommodationRepository.findAll();
            availableAccommodation.removeAll(reservedAccommodation);


            // 3. 남은 숙소들만 반환
            PageRequest pageRequest = PageRequest.of()
            return availableAccommodation;

        }
    }
}
