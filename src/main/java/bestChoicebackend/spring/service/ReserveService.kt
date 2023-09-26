package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.Reserve
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
        }

        reserveRepository.save(reserve)
    }

    fun getUserReservation(userId: Long): List<Reserve> {

        val user = userRepository.findByUserId(userId).orElse(null)
                ?: return emptyList() // 유저를 찾을 수 없을 경우 빈 리스트 반환

        return reserveRepository.findByUserId(user)
    }


    fun getReservationsByAccommodation(accommodationId: Long): List<Reserve> {
        val accommodation = accommodationRepository.findByAccommodationId(accommodationId).orElse(null)
                ?: return emptyList() // 유저를 찾을 수 없을 경우 빈 리스트 반환

        return reserveRepository.findByAccommodationId(accommodation)
    }

    fun deleteReserve(reserveId: Long) {
        reserveRepository.deleteById(reserveId)
    }
}