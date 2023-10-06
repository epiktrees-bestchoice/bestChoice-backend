package bestChoicebackend.spring.controller

import bestChoicebackend.spring.dto.UserLikeDto
import bestChoicebackend.spring.service.UserLikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/my/like")
class UserLikeController(private val userLikeService: UserLikeService) {

    @PostMapping
    fun addUserLike(@RequestBody userLikeDTO: UserLikeDto) {
        userLikeService.addUserLike(userLikeDTO)
    }

    @GetMapping("/{userId}")
    fun getUserLikesByUserId(@PathVariable userId: Long) : ResponseEntity<List<UserLikeDto>> {
        val userLikesDto = UserLikeDto(null, userId, 0)  // 0은 임의의 값
        val userLikes = userLikeService.getUserLikesByUserId(userLikesDto)
        return ResponseEntity.ok(userLikes)
    }


    @DeleteMapping("/{userLikeId}")
    fun deleteUserLike(@PathVariable userLikeId: Long) : ResponseEntity<Void> {
        userLikeService.deleteUserLike(userLikeId)
        return ResponseEntity.noContent().build()
    }
}