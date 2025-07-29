package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.scrapper.LinkDto;
import java.net.URI;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, long linkId, URI url);

    void remove(long tgChatId, long linkId);

    List<LinkDto> listAll(long tgChatId);

    /*default long linkId(URI url) {
        return url.toString().hashCode();
    }
     */
}
