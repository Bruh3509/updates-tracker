package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.domain.jdbc.ChatToLink;
import edu.java.scrapper.domain.jdbc.Link;
import edu.java.scrapper.dto.scrapper.LinkDto;
import edu.java.scrapper.service.interfaces.LinkService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JdbcLinkService implements LinkService {
    JdbcChatToLinkDao jdbcChatToLinkDao;
    JdbcLinkDao jdbcLinkDao;

    @Override
    public void add(long tgChatId, long linkId, URI url) {
        jdbcLinkDao.add(new Link(
            linkId,
            url.toString(),
            System.currentTimeMillis(),
            OffsetDateTime.now(ZoneId.of("Z"))
        ));
        jdbcChatToLinkDao.add(new ChatToLink(tgChatId, linkId));
    }

    @Override
    public void remove(long tgChatId, long linkId) {
        jdbcChatToLinkDao.remove(tgChatId, linkId);
    }

    @Override
    public List<LinkDto> listAll(long tgChatId) {
        var listOfLinks = jdbcChatToLinkDao.findAll(tgChatId);
        return listOfLinks
            .stream()
            .map(cur -> {
                var linkDto = jdbcLinkDao.findAll(cur.linkId()).getFirst();
                return new LinkDto(linkDto.id(), URI.create(linkDto.name()));
            })
            .toList();
    }
}
