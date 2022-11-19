package br.com.sekai.book_exchange.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebServerConfiguration: WebMvcConfigurer {

    @Value("\${cors.originPatterns:default}")
    private val corsOriginPatterns: String = ""


            override fun addCorsMappings(registry: CorsRegistry) {
                val allowedOrigins = corsOriginPatterns.split(",").toTypedArray()
                    registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins(*allowedOrigins)
                        .allowCredentials(true)
            }

}
