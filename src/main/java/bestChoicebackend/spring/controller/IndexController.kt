package bestChoicebackend.spring.controller

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/oauth")
class IndexController {
    @GetMapping("/logInfo")
    @ResponseBody
    fun oauthLoginInfo(authentication: Authentication): String {
        val oAuth2User = authentication.principal as OAuth2User
        val attributes = oAuth2User.attributes
        return attributes.toString()
    }

    @GetMapping("/")
    @ResponseBody
    fun home(): String {
        return "root page"
    }

    @GetMapping("/userpage")
    fun userInfo(authentication: Authentication, model: Model): String {
        val oAuth2User = authentication.principal as OAuth2User
        val attributes = oAuth2User.attributes
        model.addAttribute("data", attributes["name"])
        return "hello"
    }
}