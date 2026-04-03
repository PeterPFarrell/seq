package seq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(scanBasePackages = ["seq"])
class SeqApplication

fun main(args: Array<String>) {
	runApplication<SeqApplication>(*args)
}