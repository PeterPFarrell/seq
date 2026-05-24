package seq

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@ConfigurationProperties(prefix = "app.cors")
data class CorsProps(
    val allowed: List<String> = emptyList()
)

@EnableConfigurationProperties(CorsProps::class)
@Configuration
class SeqConfig(
    private val cors: CorsProps
) : WebMvcConfigurer {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun addCorsMappings(registry: CorsRegistry) {
        logger.info("Adding cors mappings: {}", cors.allowed.joinToString(","))

        if (cors.allowed.isEmpty() || cors.allowed == null) return

        registry.addMapping("/**")
            .allowedOriginPatterns(*cors.allowed.toTypedArray())
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
    }
}