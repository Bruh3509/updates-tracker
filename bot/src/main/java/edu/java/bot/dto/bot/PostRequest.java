package edu.java.bot.dto.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PostRequest(@JsonProperty("id") Integer id,
                          @JsonProperty("url") String url,
                          @JsonProperty("description") String description,
                          @JsonProperty("tgChatIds") List<Long> tgChatIds) {
}
