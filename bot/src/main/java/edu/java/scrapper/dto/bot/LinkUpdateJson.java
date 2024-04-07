package edu.java.scrapper.dto.bot;

import java.util.List;

public record LinkUpdateJson(Long id,
                             String name,
                             List<Long> chatId) {
}
