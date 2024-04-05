package edu.java.bot.dto.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record LinkUpdateJson(@JsonProperty Long id,
                             @JsonProperty String name,
                             @JsonProperty List<Long> chatId) {
}
