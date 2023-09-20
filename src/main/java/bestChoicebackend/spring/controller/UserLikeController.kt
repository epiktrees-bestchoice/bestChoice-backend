package bestChoicebackend.spring.controller

import bestChoicebackend.spring.domain.UserLike
import bestChoicebackend.spring.service.UserLikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/my/like")
class UserLikeController(private val userLikeService: UserLikeService) {

    @PostMapping
    fun addUserLike(@RequestBody userLike: UserLike): ResponseEntity<UserLike> {
        val createdUserLike = userLikeService.addUserLike(userLike)
        return ResponseEntity.created(URI.create("api/v1/my/like/${createdUserLike.userLikeId}")).body(createdUserLike)
    }

    @GetMapping("/{userId}")
    fun getUserLikesByUserId(@PathVariable userId: Long) : ResponseEntity<List<UserLike>> {
        val userLikes = userLikeService.getUserLikesByUserId(userId)
        return ResponseEntity.ok(userLikes)
    }
}