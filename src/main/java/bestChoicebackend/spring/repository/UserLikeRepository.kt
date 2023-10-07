package bestChoicebackend.spring.repository

import bestChoicebackend.spring.domain.User
import bestChoicebackend.spring.domain.UserLike
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikeRepository : JpaRepository<UserLike, Long> {
    fun findByUserId(user: User): List<UserLike>
}
