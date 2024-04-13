package edu.java.scrapper.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostRequest(@JsonProperty("link") Link link) {
}
