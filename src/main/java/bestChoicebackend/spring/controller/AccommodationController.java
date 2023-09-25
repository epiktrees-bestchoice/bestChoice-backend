package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import bestChoicebackend.spring.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Accommodation> findById(@PathVariable("accommodationId")Long accommodationId){
        Accommodation accommodation = accommodationService.findById(accommodationId);
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
    @GetMapping("/api/product/accommodation/all")
    public List<Accommodation> findAll(){
        return accommodationService.findAll();
    }
}
