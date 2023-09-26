package bestChoicebackend.spring.controller

import bestChoicebackend.spring.service.ReserveService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reserve")
class ReserveController(private val reserveService: ReserveService) {

}