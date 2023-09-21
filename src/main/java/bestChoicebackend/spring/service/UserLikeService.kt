package bestChoicebackend.spring.service

import bestChoicebackend.spring.dto.UserLikeDTO
import bestChoicebackend.spring.repository.AccommodationRepository
import bestChoicebackend.spring.repository.UserLikeRepository
import bestChoicebackend.spring.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserLikeService(
    private val userLikeRepository: UserLikeRepository
) {

    fun addUserLike(userLikeDTO: UserLikeDTO) {
        val userLike = userLikeRepository.findByIdOrNull(id = userLikeDTO.userLikeId)
            ?: throw EntityNotFoundException("Not found")

        userLikeRepository.save(userLike)
    }

    fun getUserLikesByUserId(userLikeDTO: UserLikeDTO): List<UserLikeDTO> {
        val userLikes = userLikeRepository.findAllByUserId_UserId(userLikeDTO.userId)

        return userLikes.map {
            UserLikeDTO(
                userLikeId = userLikeDTO.userLikeId,
                userId = userLikeDTO.userId,
                accommodationId = userLikeDTO.accommodationId
            )
        }

    }

    fun deleteUserLike(userLikeId: Long) {
        userLikeRepository.deleteById(userLikeId)
    }
}
