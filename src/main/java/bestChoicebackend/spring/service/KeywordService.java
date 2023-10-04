package bestChoicebackend.spring.service;

import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.KeywordRepository;
import bestChoicebackend.spring.repository.MTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final MTypeRepository mTypeRepository;

    public KeywordDto findByCategoryId(Long categoyId) {


    }
}
