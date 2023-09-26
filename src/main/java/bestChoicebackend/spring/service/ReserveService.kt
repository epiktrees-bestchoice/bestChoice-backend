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

    fun getUserReservation(reserveDto: ReserveDto): List<ReserveDto> {
        val reserves = reserveRepository.findByUserId(reserveDto.userId)

        return reserves.map {
            ReserveDto(
                reserveId = reserveDto.reserveId,
                userId = reserveDto.userId,
                accommodationId = reserveDto.accommodationId
            )
        }
    }

    fun getReservationsByAccommodation(reserveDto: ReserveDto): List<ReserveDto> {
        val reserves = reserveRepository.findByAccommodationId(reserveDto.accommodationId)

        return reserves.map {
            ReserveDto(
                reserveId = reserveDto.reserveId,
                userId = reserveDto.userId,
                accommodationId = reserveDto.accommodationId
            )
        }
    }

    fun deleteReserve(reserveId: Long) {
        reserveRepository.deleteById(reserveId)
    }
}