package edu.java.scrapper.dto.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PostRequest(@JsonProperty("id") Long id,
                          @JsonProperty("url") String url,
                          @JsonProperty("description") String description,
                          @JsonProperty("tgChatIds") List<Long> tgChatIds) {
}
