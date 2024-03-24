package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.jdbc.LinkDto;
import java.util.List;

public interface LinkUpdater {
    static final int FIVE_MINUTES = 300_000;
    static final String GITHUB = "https://github.com";
    static final String STACK = "https://stackoverflow.com";
    static final String SITE = "stackoverflow";

    List<LinkDto> update();
}
