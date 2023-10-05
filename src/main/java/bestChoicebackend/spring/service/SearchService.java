package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.domain.Region;
import bestChoicebackend.spring.domain.Reserve;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.ReserveRepository;
import com.nimbusds.oauth2.sdk.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final AccommodationRepository accommodationRepository;
    private final ReserveRepository reserveRepository;


    public Page<Accommodation> findByAccommodationName(String text, Pageable pageable){
        Page<Accommodation> accommodations = accommodationRepository.findByAccommodationNameContaining(text, pageable);
        return accommodations;
    }


    public Page<Accommodation> findByCondition(AccommodationType accommodationType, String region, String sort, LocalDate sel_date, LocalDate sel_date2, Long minPrice, Long maxPrice, Long reserve, String keyword, Pageable pageable) {
        /**
         * MOTEL
         */
        if(accommodationType.equals(AccommodationType.MOTEL)){
            return accommodationRepository.findByAccommodationTypeAndRegion(accommodationType, Region.from(region),pageable);
        }
        /**
         * HOTEL
         */
        else if(accommodationType.equals(AccommodationType.HOTEL)){
            // 예약 가능 여부와 관계없이 모든 숙소를 요청한 경우 reserve=0
            if(reserve==0){
                return accommodationRepository.findByAccommodationTypeAndRegion(accommodationType, Region.from(region), pageable);
            }
            // 예약 가능한 숙소만을 요청한 경우 reserve=1
            else{
                List<Accommodation> reservedAccommodation = new ArrayList<>();
                // 1. 예약을 원하는 날짜에 예약 정보가 존재하는 숙소들의 ID 가져와서 중복을 제거한다.
                for(int i=0;i< Period.between(sel_date,sel_date2).getDays()+1;i++){
                    List<Reserve> reserves = reserveRepository.findReservedAccommodation(accommodationType, Region.from(region), java.sql.Date.valueOf(sel_date.plusDays(i)));
                    for (int j=0;j<reserves.size();j++){
                        Accommodation accommodation = reserves.get(j).getAccommodationId();
                        if(!reservedAccommodation.contains(accommodation)){
                            reservedAccommodation.add(accommodation);
                        }
                    }
                }

                // 2. 모든 숙소에서 1의 숙소들을 제외한다.
                // 3. 남은 숙소들만 반환

            }
        }
        return null;
    }
}
