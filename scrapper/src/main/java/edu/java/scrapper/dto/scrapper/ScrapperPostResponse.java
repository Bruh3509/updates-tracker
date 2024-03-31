package edu.java.scrapper.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;

public record ScrapperPostResponse(@JsonProperty("id") Long id,
                                   @JsonProperty("url") URI url) {
}
