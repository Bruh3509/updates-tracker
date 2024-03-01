package edu.java.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostResponse(@JsonProperty("id") Integer id,
                                   @JsonProperty("url") String url) {
}
