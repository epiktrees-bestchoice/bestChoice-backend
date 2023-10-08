package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.UserLike
import bestChoicebackend.spring.dto.UserLikeDto
import bestChoicebackend.spring.repository.AccommodationRepository
import bestChoicebackend.spring.repository.UserLikeRepository
import bestChoicebackend.spring.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserLikeService(
    private val userLikeRepository: UserLikeRepository,
    private val userRepository: UserRepository,
    private val accommodationRepository: AccommodationRepository
) {

    fun addUserLike(userLikeDto: UserLikeDto) :UserLikeDto {
        val user = userRepository.findById(userLikeDto.userId)
            .orElseThrow { EntityNotFoundException("Not found") }
        val accommodation = accommodationRepository.findById(userLikeDto.accommodationId)
            .orElseThrow { EntityNotFoundException("Not found") }

        val userLike = UserLike().apply {
            this.userId = user
            this.accommodationId = accommodation
        }

        val savedLike = userLikeRepository.save(userLike)
        return UserLikeDto(savedLike.userLikeId, savedLike.userId.userId, savedLike.accommodationId.accommodationId)
    }

    fun getUserLikesByUserId(userId: Long): List<UserLikeDto> {
        val user = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User not found") }

        return userLikeRepository.findByUserId(user).map {
            UserLikeDto(it.userLikeId, it.userId.userId, it.accommodationId.accommodationId)
        }
    }


    fun deleteUserLike(userLikeId: Long) : Long {
        userLikeRepository.deleteById(userLikeId)
        return userLikeId
    }
}
