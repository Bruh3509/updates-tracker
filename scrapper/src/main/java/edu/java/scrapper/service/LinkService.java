package edu.java.scrapper.service;

import edu.java.scrapper.dto.jdbc.LinkDto;
import java.net.URI;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, URI url);

    void remove(long tgChatId, URI url);

    List<LinkDto> listAll(long tgChatId);
}
