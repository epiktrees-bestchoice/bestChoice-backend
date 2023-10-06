package bestChoicebackend.spring.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationKeyword;
import bestChoicebackend.spring.domain.AccommodationType;
import bestChoicebackend.spring.dto.KeywordDto;
import bestChoicebackend.spring.repository.AccommodationKeywordRepository;
import bestChoicebackend.spring.repository.AccommodationRepository;
import bestChoicebackend.spring.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@RequiredArgsConstructor
public class AccommodationKeywordService {
    private final AccommodationKeywordRepository accommodationKeywordRepository;
    private final AccommodationRepository accommodationRepository;
    private final KeywordService keywordService;
    private final KeywordRepository keywordRepository;

    public List<KeywordDto.KeywordType> getRandomKeywordTypes(List<List<KeywordDto.KeywordType>> keywordLists) {
        List<KeywordDto.KeywordType> selectedKeywordTypes = new ArrayList<>();
        Random random = new Random();

        for (List<KeywordDto.KeywordType> keywordTypeList : keywordLists) {
            for (KeywordDto.KeywordType keywordType : keywordTypeList) {
                // 20% 확률로 KeywordType을 선택
                if (random.nextDouble() < 0.4) {
                    selectedKeywordTypes.add(keywordType);
                }
            }
        }

        return selectedKeywordTypes;
    }

    public Long createInitKeyword(){
        List<Accommodation> accommodations = accommodationRepository.findAll();
        List<KeywordDto> keywordDtos = new ArrayList<>(List.of(keywordService.findByCategoryId((long) 1),
                keywordService.findByCategoryId((long) 2),
                keywordService.findByCategoryId((long) 3),
                keywordService.findByCategoryId((long) 4),
                keywordService.findByCategoryId((long) 5)));
        List<String> categoryIndex = new ArrayList<>(Arrays.asList("HOTEL","MOTEL","PENSION","GUESTHOUSE","CAMPING"));

        Long count = (long) 0;

        for(Accommodation nowAccommodation : accommodations){
            int categoryId = categoryIndex.indexOf(nowAccommodation.getType().name());
            List<KeywordDto.KeywordType> selectedKeywordTypes = getRandomKeywordTypes(keywordDtos.get(categoryId).getKeywordList());

            for(KeywordDto.KeywordType keywordType : selectedKeywordTypes){
                count++;
                AccommodationKeyword accommodationKeyword = new AccommodationKeyword();
                accommodationKeyword.setAccommodationId(nowAccommodation);
                accommodationKeyword.setKeywordId(keywordRepository.findByKeywordId(keywordType.getKeywordId()));
                accommodationKeywordRepository.save(accommodationKeyword);
            }
        }
        return count;
    }
}
