package bestChoicebackend.spring.dto

import java.util.Date

data class ReserveDto(
    val reserveId: Long?,
    val userId: Long,
    val accommodationId: Long,
    val reserveDate: Date,
    val endDate: Date
)
