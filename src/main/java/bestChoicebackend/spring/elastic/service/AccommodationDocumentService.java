package bestChoicebackend.spring.elastic.service;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.elastic.document.AccommodationDocument;
import bestChoicebackend.spring.elastic.repository.AccommodationDocumentRepository;
import bestChoicebackend.spring.exception.BaseException;
import bestChoicebackend.spring.exception.BaseResponseStatus;
import bestChoicebackend.spring.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccommodationDocumentService {
    private final AccommodationDocumentRepository accommodationDocumentRepository;
    private final AccommodationRepository accommodationRepository;
    public void save(final AccommodationDocument accom){
        log.info("save id : "+accom.getId()+" name : "+accom.getAccommodationName());
        accommodationDocumentRepository.save(accom);
    }

    public AccommodationDocument findById(final String id){
        AccommodationDocument person = accommodationDocumentRepository.findById(id).orElse(null);
        if(person == null){
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
        log.info("save id : "+person.getId()+" name : "+person.getAccommodationName());
        return person;
    }

    public int elasticInit(){
        Long topId = accommodationDocumentRepository.findTopByOrderByIdDesc();
        List<Accommodation> accoms = accommodationRepository.findByAccommodationIdGreaterThan(topId);
        List<AccommodationDocument> accommodationDocumentList = new ArrayList<>();

        for(Accommodation accommodation : accoms){
            AccommodationDocument accommodationDocument = getAccommodationDocument(accommodation);
            accommodationDocumentList.add(accommodationDocument);
        }
        log.info("save accoms count : "+accommodationDocumentList.size());
        accommodationDocumentRepository.saveAll(accommodationDocumentList);
        return accoms.size();
    }

    @NotNull
    private static AccommodationDocument getAccommodationDocument(Accommodation accommodation) {
        AccommodationDocument accommodationDocument = new AccommodationDocument();
        accommodationDocument.setId(String.valueOf(accommodation.getAccommodationId()));
        accommodationDocument.setAccommodationName(accommodation.getAccommodationName());
        accommodationDocument.setType(accommodation.getType().getType()); // Enum 값을 가져옵니다.
        accommodationDocument.setRegion(accommodation.getRegion());
        accommodationDocument.setIntroduce(accommodation.getIntroduce());
        return accommodationDocument;
    }

    public List<AccommodationDocument> customFullTextSearch(final String name){
        List<AccommodationDocument> accommodationDocumentList = accommodationDocumentRepository.customFullTextSearch(name);
        log.info("get accommodations count : "+ accommodationDocumentList.size());
        return accommodationDocumentList;
    }

    public List<AccommodationDocument> findByAccommodationDocumentName(final String name){
        List<AccommodationDocument> accommodationDocumentList = accommodationDocumentRepository.findByAccommodationName(name);
        log.info("get accommodations count : "+ accommodationDocumentList.size());
        return accommodationDocumentList;
    }
}
