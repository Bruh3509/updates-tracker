package edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull
    Scheduler scheduler,
    @NotNull
    AccessType databaseAccessType,
    @NotNull
    Retry retry,
    @NotNull
    Kafka kafka,
    @NotNull
    boolean useQueue
) {
    public record Scheduler(boolean enable, @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }

    public enum AccessType {
        JDBC, JPA, JOOQ
    }

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
