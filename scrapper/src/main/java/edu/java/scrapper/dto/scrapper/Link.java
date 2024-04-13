package edu.java.scrapper.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record Link(@JsonProperty("id") Long id,
                   @JsonProperty("url") URI url) {
}
