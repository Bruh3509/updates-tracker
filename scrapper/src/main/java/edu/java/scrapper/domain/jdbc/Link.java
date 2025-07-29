package edu.java.scrapper.domain.jdbc;

import java.time.OffsetDateTime;

public record Link(long id, String name, long curTime, OffsetDateTime lastUpdate) {
}
