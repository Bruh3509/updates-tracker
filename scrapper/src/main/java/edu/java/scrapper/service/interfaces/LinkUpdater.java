package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.bot.LinkUpdate;
import java.util.List;

public interface LinkUpdater {
    int FIVE_MINUTES = 300_000;
    enum SITE {
        GITHUB("https://github.com"),
        STACK ("https://stackoverflow.com");

        SITE(String url) {
        }
    }

    enum PARSE {
        SITE("stackoverflow");

        PARSE(String stackoverflow) {
        }
    }

    List<LinkUpdate> update();
}
