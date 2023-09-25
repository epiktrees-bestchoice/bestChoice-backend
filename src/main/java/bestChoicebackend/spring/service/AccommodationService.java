package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.repostiory.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
