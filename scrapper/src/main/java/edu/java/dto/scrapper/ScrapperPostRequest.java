package edu.java.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostRequest(@JsonProperty("link") String link) {
}
