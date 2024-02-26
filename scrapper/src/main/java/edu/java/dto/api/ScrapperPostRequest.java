package edu.java.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScrapperPostRequest(@JsonProperty("link") String link) {
}
