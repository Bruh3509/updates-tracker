package edu.java.scrapper.dto.jdbc;

import java.time.OffsetDateTime;

public record LinkDto(long id, String name, long curTime, OffsetDateTime lastUpdate) {
}
