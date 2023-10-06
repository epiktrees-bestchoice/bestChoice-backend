package bestChoicebackend.spring.controller

import bestChoicebackend.spring.dto.ReserveDto
import bestChoicebackend.spring.service.ReserveService
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
    fun addReservation(@RequestParam reserveDto: ReserveDto) {
        reserveService.addReservation(reserveDto)
    }

    @GetMapping("/user/accommodations")
    fun getUserReservation(@RequestParam reserveDto: ReserveDto) : ResponseEntity<List<ReserveDto>> {
        val userReservations = reserveService.getUserReservation(userId = reserveDto.userId)

        return ResponseEntity.ok(userReservations)
    }

    @GetMapping("/product/accommodations")
    fun getReservationsByAccommodation(@RequestParam reserveDto: ReserveDto) : ResponseEntity<List<ReserveDto>> {
        val reservations = reserveService.getReservationsByAccommodation(accommodationId = reserveDto.accommodationId)

        return ResponseEntity.ok(reservations)
    }

    @DeleteMapping("/{reserveId}")
    fun deleteReservation(@PathVariable reserveId: Long) : ResponseEntity<Void> {
        reserveService.deleteReserve(reserveId)
        return ResponseEntity.noContent().build()
    }
}