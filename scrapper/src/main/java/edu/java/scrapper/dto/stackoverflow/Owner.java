package edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Owner(@JsonProperty Integer id,
                    @JsonProperty Integer reputation,
                    @JsonProperty Integer userId,
                    @JsonProperty String userType,
                    @JsonProperty String profileImage,
                    @JsonProperty String displayName,
                    @JsonProperty String link
) {
}
