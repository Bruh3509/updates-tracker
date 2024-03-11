package edu.java.bot.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record GetResponse(@JsonProperty("links") List<Link> links,
                          @JsonProperty("size") Integer size) {
}
