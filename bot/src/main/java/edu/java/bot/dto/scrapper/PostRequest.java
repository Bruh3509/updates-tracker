package edu.java.bot.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostRequest(@JsonProperty("link") Link link) {
}
