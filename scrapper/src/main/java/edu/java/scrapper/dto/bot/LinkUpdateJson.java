package edu.java.scrapper.dto.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record LinkUpdateJson(@JsonProperty Long id,
                             @JsonProperty String name,
                             @JsonProperty List<Long> chatId) {
}
