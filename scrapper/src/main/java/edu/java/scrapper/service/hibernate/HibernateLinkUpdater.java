package edu.java.scrapper.service.hibernate;

import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class HibernateLinkUpdater implements LinkUpdater {
    @Override
    public List<LinkUpdate> update() {
        return List.of();
    }
}
