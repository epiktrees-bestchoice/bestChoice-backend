package bestChoicebackend.spring.service

import bestChoicebackend.spring.domain.UserLike
import bestChoicebackend.spring.repository.UserLikeRepository
import org.springframework.stereotype.Service

@Service
class UserLikeService(private val userLikeRepository: UserLikeRepository) {
    fun addUserLike(userLike: UserLike): UserLike {
        return userLikeRepository.save(userLike)
    }

    fun getUserLikesByUserId(userId: Long): List<UserLike> {
        return userLikeRepository.findAllByUserId(userId)
    }
}