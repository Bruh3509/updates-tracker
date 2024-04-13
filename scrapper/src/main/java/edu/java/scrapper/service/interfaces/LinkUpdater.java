package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.bot.LinkUpdate;
import java.util.List;

public interface LinkUpdater {
    int FIVE_MINUTES = 300_000;
    String GITHUB = "https://github.com";
    String STACK = "https://stackoverflow.com";
    String SITE = "stackoverflow";

    List<LinkUpdate> update();
}
