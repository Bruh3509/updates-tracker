package edu.java.scrapper.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

// maybe some fields should be added.
public record RepositoryDto(@JsonProperty("id") Long id,
                            @JsonProperty("full_name") String name,
                            @JsonProperty("html_url") String link,
                            @JsonProperty("created_at") OffsetDateTime createDate,
                            @JsonProperty("updated_at") OffsetDateTime updateDate,
                            @JsonProperty("pushed_at") OffsetDateTime pushDate,
                            @JsonProperty("language") String language) {
}
