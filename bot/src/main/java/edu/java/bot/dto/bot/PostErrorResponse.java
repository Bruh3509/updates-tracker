package edu.java.bot.dto.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PostErrorResponse(@JsonProperty("description") String description,
                                @JsonProperty("code") String code,
                                @JsonProperty("exceptionName") String exceptionName,
                                @JsonProperty("exceptionMessage") String exceptionMessage,
                                @JsonProperty("stacktrace") List<String> stacktrace) {
}
