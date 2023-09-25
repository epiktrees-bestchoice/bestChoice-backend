package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.repostiory.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final AccommodationRepository accommodationRepository;


    public Page<Accommodation> findByAccommodationName(String text, Pageable pageable){
        Page<Accommodation> accommodations = accommodationRepository.findByAccommodationNameContaining(text, pageable);
        return accommodations;
    }
}
