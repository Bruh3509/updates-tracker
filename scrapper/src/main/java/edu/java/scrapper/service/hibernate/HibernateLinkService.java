package edu.java.scrapper.service.hibernate;

import edu.java.scrapper.dao.hibernate.HibernateChatDao;
import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.dto.scrapper.LinkDto;
import edu.java.scrapper.entity.Link;
import edu.java.scrapper.service.interfaces.LinkService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class HibernateLinkService implements LinkService {
    HibernateLinkDao linkDao;
    HibernateChatDao chatDao;

    @Override
    public void add(long tgChatId, long linkId, URI url) {
        var chat = chatDao.findById(tgChatId)
            .orElseThrow(() -> new EntityNotFoundException("Chat with id " + tgChatId + " not found"));
        var link = linkDao.findById(linkId)
            .orElse(new Link(
                linkId,
                url.toString(),
                System.currentTimeMillis(),
                OffsetDateTime.now(ZoneId.of("Z"))
            ));

        link.getFollowingChats().add(chat);
        chat.getFollowingLinks().add(link);

        linkDao.add(link);
    }

    @Override
    public void remove(long tgChatId, long linkId) {
        var chatO = chatDao.findById(linkId)
            .orElseThrow(() -> new EntityNotFoundException("Chat with id " + linkId + " not found"));
        var linkO = linkDao.findById(linkId)
            .orElseThrow(() -> new EntityNotFoundException("Link with id " + linkId + " not found"));

        chatO.getFollowingLinks().remove(linkO);
        linkO.getFollowingChats().remove(chatO);
    }

    // TODO verify
    @Override
    public List<LinkDto> listAll(long tgChatId) {
        var chatO = chatDao.findById(tgChatId)
            .orElseThrow(() -> new EntityNotFoundException("Chat with id " + tgChatId + " not found"));
        return chatO.getFollowingLinks()
            .stream()
            .map(link -> new LinkDto(link.getId(), URI.create(link.getName())))
            .toList();
    }
}
