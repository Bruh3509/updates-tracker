package edu.java.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostResponse(@JsonProperty("id") Integer id,
                                   @JsonProperty("url") String url) {
}
