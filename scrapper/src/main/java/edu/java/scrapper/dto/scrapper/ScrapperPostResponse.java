package edu.java.scrapper.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostResponse(@JsonProperty("id") Long id,
                                   @JsonProperty("url") String url) {
}
