package edu.java.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Link(@JsonProperty("id") Integer id,
                   @JsonProperty("url") String string) {
}
