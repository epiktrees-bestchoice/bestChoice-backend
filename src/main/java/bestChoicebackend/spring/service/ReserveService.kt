package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.Reserve
import bestChoicebackend.spring.domain.User
import bestChoicebackend.spring.dto.ReserveDto
import bestChoicebackend.spring.repository.AccommodationRepository
import bestChoicebackend.spring.repository.ReserveRepository
import bestChoicebackend.spring.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val userRepository: UserRepository,
    private val accommodationRepository: AccommodationRepository
) {

    fun addReservation(reserveDto: ReserveDto) {
        val user = userRepository.findById(reserveDto.userId)
            .orElseThrow {EntityNotFoundException("Not found")}

        val accommodation = accommodationRepository.findById(reserveDto.accommodationId)
            .orElseThrow { EntityNotFoundException("Not found") }

        val reserve = Reserve().apply {
            this.userId = user
            this.accommodationId = accommodation
            this.reserveDate = reserveDto.reserveDate
            this.endDate = reserveDto.endDate
        }
        reserveRepository.save(reserve)
    }

    fun getUserReservation(userId: Long): List<ReserveDto> {
        val user = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("Not found") }

        return reserveRepository.findByUserId(user).map {
            ReserveDto(it.reserveId, it.userId.userId, it.accommodationId.accommodationId, it.reserveDate, it.endDate)
        }
    }

    fun getReservationsByAccommodation(accommodationId: Long): List<ReserveDto> {
        val accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow { EntityNotFoundException("Accommodation not found") }

        return reserveRepository.findByAccommodationId(accommodation).map {
            ReserveDto(it.reserveId, it.userId.userId, it.accommodationId.accommodationId, it.reserveDate, it.endDate)
        }
    }

    fun deleteReserve(reserveId: Long) {
        reserveRepository.deleteById(reserveId)
    }
}