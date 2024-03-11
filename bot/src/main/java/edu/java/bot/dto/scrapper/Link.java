package edu.java.bot.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record Link(@JsonProperty("id") Integer id,
                   @JsonProperty("url") URI url) {
}
