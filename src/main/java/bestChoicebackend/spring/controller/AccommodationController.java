package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.domain.AccommodationKeyword;
import bestChoicebackend.spring.dto.accommodationDto.AccommodationResDto;
import bestChoicebackend.spring.service.AccommodationKeywordService;
import bestChoicebackend.spring.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccommodationController {
    private final AccommodationService accommodationService;

    /**
     * 숙소 상세
     * 숙소 ID로 찾아서 숙소 객체 반환
     */
    @GetMapping("/api/product/accommodation/{accommodationId}")
    public ResponseEntity<AccommodationResDto> findById(@PathVariable("accommodationId")Long accommodationId){
        AccommodationResDto accommodation = accommodationService.findById(accommodationId);
        if(accommodation!=null){
            return ResponseEntity.status(HttpStatus.OK).body(accommodation);
        }
        else{
            log.info("Accommodation NOT FOUND");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 전체 숙소 List 반환
     */
//    @GetMapping("/api/product/accommodation/all")
//    public List<Accommodation> findAll(){
//        return accommodationService.findAll();
//    }
    @GetMapping("/api/product/accommodation/dumy/{accommodationType}")
    public List<Accommodation> findByAccommodationType(@PathVariable("accommodationType") String accommodationType){
        return accommodationService.findByAccommodationType(accommodationType);
    }

    @GetMapping("/api/product/accommodation/createinit")
    public List<Accommodation> createInit() {return accommodationService.createInit();}

    @PostMapping("/api/product/accommodation/trash")
    public void deleteAll(){
        accommodationService.deleteAll();
    }



}
