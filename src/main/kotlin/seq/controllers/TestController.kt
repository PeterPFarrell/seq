package seq.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController() {
    val s = "Welcome to the backend fool"

    @GetMapping("/test")
    fun test(): String {
        return s
    }
}