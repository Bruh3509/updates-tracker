package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.dto.jdbc.ChatToLinkDto;
import edu.java.scrapper.dto.jdbc.LinkDto;
import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.service.interfaces.LinkService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcLinkService implements LinkService {
    private final JdbcChatToLinkDao jdbcChatToLinkDao;
    private final JdbcLinkDao jdbcLinkDao;
    private final JdbcChatDao jdbcChatDao;

    @Autowired
    public JdbcLinkService(JdbcChatToLinkDao jdbcChatToLinkDao, JdbcLinkDao jdbcLinkDao, JdbcChatDao jdbcChatDao) {
        this.jdbcChatToLinkDao = jdbcChatToLinkDao;
        this.jdbcLinkDao = jdbcLinkDao;
        this.jdbcChatDao = jdbcChatDao;
    }

    @Override
    public void add(long tgChatId, URI url) {
        long linkId = linkId(url);
        jdbcLinkDao.add(new LinkDto(linkId, url.toString(), System.currentTimeMillis(), OffsetDateTime.now()));
        jdbcChatToLinkDao.add(new ChatToLinkDto(tgChatId, linkId));
    }

    @Override
    public void remove(long tgChatId, URI url) {
        jdbcChatToLinkDao.remove(tgChatId, linkId(url));
    }

    @Override
    public List<Link> listAll(long tgChatId) {
        var listOfLinks = jdbcChatToLinkDao.findAll(tgChatId);
        return listOfLinks
            .stream()
            .map(cur ->  {
                var linkDto = jdbcLinkDao.findAll(cur.linkId()).getFirst();
                return new Link(linkDto.id(), linkDto.name());
            })
            .toList();
    }
}
