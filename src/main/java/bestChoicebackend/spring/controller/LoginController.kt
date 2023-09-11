package bestChoicebackend.spring.controller

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.http.javanet.NetHttpTransport
//최신버전이기 때문에 Jackson 대신 Gson 을 사용
import com.google.api.client.json.gson.GsonFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(model: Model): String {
        val flow = GoogleAuthorizationCodeFlow.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            "1094047459873-pi6490qvic9rk99agsfli725cvpk5q88.apps.googleusercontent.com",
            "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",
            listOf("profile", "email")
        ).build()

        val url = flow.newAuthorizationUrl()

        model.addAttribute("loginUrl", url)

        return "login"
    }

    @GetMapping("/login/callback")
    fun loginCallback(model: Model, @RequestParam code: String):String {
        val flow = GoogleAuthorizationCodeFlow.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            "1094047459873-pi6490qvic9rk99agsfli725cvpk5q88.apps.googleusercontent.com",
            "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",
            listOf("profile", "email")
        ).build()

        val tokenResponse = flow.newTokenRequest(code).execute()

        val idToken = tokenResponse.parseIdToken()
        val payload = idToken.payload

        model.addAttribute("user", payload)

        return "home"
    }
}