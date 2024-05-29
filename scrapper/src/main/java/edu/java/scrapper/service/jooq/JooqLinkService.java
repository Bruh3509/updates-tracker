package edu.java.scrapper.service.jooq;

import edu.java.scrapper.dao.jooq.JooqChatToLinkDao;
import edu.java.scrapper.dao.jooq.JooqLinkDao;
import edu.java.scrapper.domain.jdbc.ChatToLinkDto;
import edu.java.scrapper.domain.jdbc.LinkDto;
import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.service.interfaces.LinkService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class JooqLinkService implements LinkService {
    private final JooqLinkDao linkDao;
    private final JooqChatToLinkDao chatToLinkDao;

    @Autowired
    public JooqLinkService(JooqLinkDao linkDao, JooqChatToLinkDao chatToLinkDao) {
        this.linkDao = linkDao;
        this.chatToLinkDao = chatToLinkDao;
    }

    @Override
    public void add(long tgChatId, long linkId, URI url) {
        // adding link if not exists to LINK table
        linkDao.add(new LinkDto(
            linkId,
            url.toString(),
            System.currentTimeMillis(),
            OffsetDateTime.now(ZoneId.of("Z"))
        ));

        // adding link to CHAT_TO_LINK relation
        chatToLinkDao.add(new ChatToLinkDto(tgChatId, linkId));
    }

    @Override
    public void remove(long tgChatId, long linkId) {
        // remove link from CHAT_TO_LINK relation
        chatToLinkDao.remove(tgChatId, linkId);
    }

    @Override
    public List<Link> listAll(long tgChatId) {
        return chatToLinkDao.listAll(tgChatId);
    }
}
