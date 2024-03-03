package edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record Item(@JsonProperty("tags") List<String> tags,
                   @JsonProperty("owner") Owner owner,
                   @JsonProperty("postState") String postState,
                   @JsonProperty("is_answered") Boolean isAnswered,
                   @JsonProperty("view_count") Integer viewCount,
                   @JsonProperty("accepted_answer_id") Integer acceptedAnsId,
                   @JsonProperty("answer_count") Integer answerCount,
                   @JsonProperty("last_activity_date") OffsetDateTime lastActDate,
                   @JsonProperty("creation_date") OffsetDateTime createDate,
                   @JsonProperty("question_id") Integer questionId,
                   @JsonProperty("link") String link,
                   @JsonProperty("title") String title
) {
}
