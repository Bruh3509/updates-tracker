package edu.java.scrapper.service.interfaces;

import edu.java.scrapper.dto.scrapper.Link;
import java.net.URI;
import java.util.List;

public interface LinkService {
    void add(long tgChatId, URI url);

    void remove(long tgChatId, URI url);

    List<Link> listAll(long tgChatId);

    default long linkId(URI url) {
        return url.toString().hashCode();
    }
}
