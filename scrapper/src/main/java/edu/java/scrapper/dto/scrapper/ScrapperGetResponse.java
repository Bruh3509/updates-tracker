package edu.java.scrapper.dto.scrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ScrapperGetResponse(@JsonProperty("links") List<LinkDto> linkDtos,
                                  @JsonProperty("size") Integer size) {
}
