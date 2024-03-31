package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@EnableCaching
public record ApplicationConfig(
    @NotEmpty
    String telegramToken,
    @NotNull
    Retry retry
) {
    public record Retry(@NotNull int maxAttempts, @NotNull long delay, @NotNull long exponential) {
    }
}
