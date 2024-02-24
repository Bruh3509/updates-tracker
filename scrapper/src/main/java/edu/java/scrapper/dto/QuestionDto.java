package edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuestionDto(@JsonProperty Integer id, @JsonProperty String title, @JsonProperty String body) {
}
