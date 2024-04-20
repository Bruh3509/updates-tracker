package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
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
        @NotNull String groupId,
        @NotNull String autoOffsetReset,
        @NotNull int maxPollIntervalMs,
        @NotNull boolean enableAutoCommit,
        @NotNull int concurrency
    ) {
    }
}
