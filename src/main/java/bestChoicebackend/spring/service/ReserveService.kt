package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.Reserve
import bestChoicebackend.spring.domain.User
import bestChoicebackend.spring.dto.ReserveDto
import bestChoicebackend.spring.repository.AccommodationRepository
import bestChoicebackend.spring.repository.ReserveRepository
import bestChoicebackend.spring.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class ReserveService(
    private val reserveRepository: ReserveRepository,
    private val userRepository: UserRepository,
    private val accommodationRepository: AccommodationRepository
) {

    fun addReservation(reserveDto: ReserveDto) : ReserveDto{
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

        // 해당 숙소의 예약 확인
        val oldReserves = reserveRepository.findByAccommodationId(accommodation)
        for(oldReserve in oldReserves){
            // 추가 예약과 날짜가 겹치는지 확인
            val oldStartDate = oldReserve.reserveDate
            val oldEndDate = oldReserve.endDate
            System.out.println("---- 이전 예약 시작 $oldStartDate ------")
            System.out.println("예약 끝 $oldEndDate")

            val cmpStartEnd = reserveDto.reserveDate.compareTo(oldEndDate)
            val cmpStartStart = reserveDto.reserveDate.compareTo(oldStartDate)
            val cmpEndStart = reserveDto.endDate.compareTo(oldStartDate)
            val cmpEndEnd = reserveDto.endDate.compareTo(oldEndDate)

            if( cmpStartEnd < 0 && cmpStartStart >= 0 ){
                throw Exception("Already reserved.")
            }
            else if (cmpEndStart >= 0 && cmpEndEnd <0){
                throw Exception("Already reserved.")
            }
        }
        val userReserve = reserveRepository.save(reserve)
        return ReserveDto(reserveId = userReserve.reserveId,
                userId = userReserve.userId.userId,
                accommodationId = userReserve.accommodationId.accommodationId,
                reserveDate = userReserve.reserveDate,
                endDate = userReserve.endDate)
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
        val deleteReserve = reserveRepository.findByReserveId(reserveId)
        reserveRepository.delete(deleteReserve)
    }
}