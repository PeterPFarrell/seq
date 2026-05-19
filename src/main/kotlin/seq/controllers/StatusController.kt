package seq.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import seq.services.StatusService


@RestController
@RequestMapping("/status")
class StatusController (
    private val statusService: StatusService
) {
    @GetMapping("/containers")
    fun getContainerInfo() : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FOUND).body(statusService.getContainerInfo())
    }
}