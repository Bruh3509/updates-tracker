package edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ItemsDto(@JsonProperty("items") List<ItemDto> itemDtos) {
}
