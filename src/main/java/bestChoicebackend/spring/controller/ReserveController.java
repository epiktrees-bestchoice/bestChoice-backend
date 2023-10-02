package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.config.auth.dto.SessionUser;
import bestChoicebackend.spring.domain.Reserve;
import bestChoicebackend.spring.domain.User;
import bestChoicebackend.spring.dto.ReserveDto;
import bestChoicebackend.spring.repository.ReserveRepository;
import bestChoicebackend.spring.service.ReserveService;
import bestChoicebackend.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;
    private final ReserveRepository reserveRepository;
    private final UserService userService;
    @PostMapping("/user/accommodation")
    public ReserveDto accommodationAdd(
            @RequestBody ReserveDto reserveDto,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ){

        LocalDate currentDate = LocalDate.now();

        // startDate 가 null 이면 현재 날짜를 사용
        if (startDate == null) {
            startDate = currentDate;
        }

        // endDate 가 null 이면 현재 날짜를 사용
        if (endDate == null) {
            endDate = currentDate;
        }

        // 시작 날짜와 끝 날짜 사이의 기간 계산
        long period = ChronoUnit.DAYS.between(startDate, endDate);

        List<Reserve> reservationsOfAccommodation = reserveService.getReservationsByAccommodation(reserveDto.getAccommodationId());

        // 현재 숙박 업소가 예약이 없는 경우,
        if (!reservationsOfAccommodation.isEmpty()) {

            for (Reserve existingReservation : reservationsOfAccommodation) {

                Reserve reservedInfo = reserveRepository.findByReserveId(existingReservation.getReserveId());

                // 기존 예약의 시작일과 종료일
//                LocalDate existingStartDate = reservedInfo.getReserveDate().toLocalDate();
                LocalDate existingStartDate = LocalDate.ofInstant(reservedInfo.getReserveDate().toInstant(), ZoneId.systemDefault());
                LocalDate existingEndDate = existingStartDate.plusDays(reservedInfo.getStayDay());

                // 새로운 예약의 시작일과 종료일
                LocalDate newReservationEndDate = startDate.plusDays(period);

                // 예약이 겹치는 경우
                if (!(newReservationEndDate.isBefore(existingStartDate) || startDate.isAfter(existingEndDate))) {
                    // 겹치는 경우에는 0L, 0, 0을 반환
                    return new ReserveDto(0L, 0, 0);
                }
            }

            // 겹치는 예약이 없는 경우, 새로운 예약 추가
        }
        reserveService.addReservation(reserveDto);
        return reserveDto;
    }

    @GetMapping("/user/accommodations")
    public List<Reserve> getUserReserve(@RequestParam(name = "userId") Long userId, HttpSession httpSession){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userService.findByUserEmail(sessionUser.getEmail())
                .orElseThrow();
        if(user.getUserId().equals(userId)){
            return reserveService.getUserReservation(userId);
        }
        else{
            return null;
        }

    }

    @GetMapping("/product/accommodations")
    public List<Reserve> getAccommodationReserve(@RequestParam(name = "accommodationId") Long accommodationId){
        return reserveService.getReservationsByAccommodation(accommodationId);
    }
}
