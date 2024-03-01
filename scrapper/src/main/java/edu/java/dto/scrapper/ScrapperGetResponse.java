package edu.java.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ScrapperGetResponse(@JsonProperty("links") List<Link> links,
                                  @JsonProperty("size") Integer size) {
}
