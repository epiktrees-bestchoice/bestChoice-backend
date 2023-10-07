package bestChoicebackend.spring.controller

import bestChoicebackend.spring.config.auth.dto.SessionUser
import bestChoicebackend.spring.dto.ReserveDto
import bestChoicebackend.spring.service.ReserveService
import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/reserve")
class ReserveController(private val reserveService: ReserveService) {

    @PostMapping("/user/accommodation")
    fun addReservation(@RequestBody reserveDto: ReserveDto, session: HttpSession) : ResponseEntity<ReserveDto> {
        val sessionUser = session.getAttribute("user") as (SessionUser)
        return if (reserveDto.userId == sessionUser.getUserId()) {
            try{
                val userReserveDto = reserveService.addReservation(reserveDto)
                ResponseEntity.ok(userReserveDto)
            }
            catch (e : Exception){
                ResponseEntity.status(HttpStatus.CONFLICT).build()
            }
        }
        else{
            // 401 Unauthorized 에러 응답
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @GetMapping("/user/accommodations")
    fun getUserReservation(@RequestParam userId: Long, session: HttpSession) : ResponseEntity<List<ReserveDto>> {
        val sessionUser = session.getAttribute("user") as (SessionUser)

        return if (userId == sessionUser.getUserId()) {
            val userReservations = reserveService.getUserReservation(userId = userId)
            ResponseEntity.ok(userReservations)
        } else {
            // 401 Unauthorized 에러 응답
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @GetMapping("/product/accommodations")
    fun getReservationsByAccommodation(@RequestParam accommodationId : Long) : ResponseEntity<List<ReserveDto>> {
        return try{
            val reservations = reserveService.getReservationsByAccommodation(accommodationId = accommodationId)
            ResponseEntity.ok(reservations)
        }
        catch (e : Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @DeleteMapping("/{reserveId}")
    fun deleteReservation(@PathVariable reserveId: Long) : ResponseEntity<Void> {
        reserveService.deleteReserve(reserveId)
        return ResponseEntity.noContent().build()
    }
}