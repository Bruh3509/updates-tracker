package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.jdbc.LinkDto;
import java.util.List;

public interface LinkUpdater {
    List<LinkDto> update();
}
