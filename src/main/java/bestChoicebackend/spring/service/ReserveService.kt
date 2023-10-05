package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.Reserve
import bestChoicebackend.spring.domain.User
import bestChoicebackend.spring.dto.ReserveDto
import bestChoicebackend.spring.repository.AccommodationRepository
import bestChoicebackend.spring.repository.ReserveRepository
import bestChoicebackend.spring.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val userRepository: UserRepository,
    private val accommodationRepository: AccommodationRepository,
) {

    fun addReservation(reserveDto: ReserveDto) {
        val reserve = reserveRepository.findByReserveId(reserveDto.reserveId)

        reserveRepository.save(reserve)
    }

    fun getUserReservation(reserveDto: ReserveDto): Optional<ReserveDto>? {
        val user = userRepository.findById(reserveDto.userId)

        return user.map {
            ReserveDto(
                reserveId = reserveDto.reserveId,
                userId = reserveDto.userId,
                accommodationId = reserveDto.accommodationId,
                reserveDate = reserveDto.reserveDate,
                endDate = reserveDto.endDate
            )
        }
    }

    fun getReservationsByAccommodation(reserveDto: ReserveDto): Optional<ReserveDto>? {
        val accommodation = accommodationRepository.findById(reserveDto.accommodationId)

        return accommodation.map {
            ReserveDto(
                reserveId = reserveDto.reserveId,
                userId = reserveDto.userId,
                accommodationId = reserveDto.accommodationId,
                reserveDate = reserveDto.reserveDate,
                endDate = reserveDto.endDate
            )
        }
    }

    fun deleteReserve(reserveId: Long) {
        reserveRepository.deleteById(reserveId)
    }
}