package edu.java.bot.dto.bot;

import java.util.List;

public record SendUpdateDto(List<Long> chatId, String name) {
}
