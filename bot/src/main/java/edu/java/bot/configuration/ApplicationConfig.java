package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.validation.annotation.Validated;
import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@EnableCaching
public record ApplicationConfig(
    @NotEmpty
    String telegramToken,
    @NotNull
    Retry retry,
    @NotNull
    Kafka kafka,
    @NotNull
    boolean useQueue
) {
    public record Retry(@NotNull int maxAttempts, @NotNull long delay, @NotNull long exponential) {
    }

    public record Kafka(
        @NotNull String bootstrapServers,
        @NotNull String clientId,
        @NotNull String acksMode,
        @NotNull Duration deliveryTimeout,
        @NotNull int lingerMs,
        @NotNull int batchSize,
        @NotNull int maxInFlightPerConnection,
        @NotNull boolean enableIdempotence
    ) {
    }
}
