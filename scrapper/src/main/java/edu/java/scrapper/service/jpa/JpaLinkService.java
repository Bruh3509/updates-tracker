package edu.java.scrapper.service.jpa;

import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.entity.Chat;
import edu.java.scrapper.repository.ChatRepository;
import edu.java.scrapper.repository.LinkRepository;
import edu.java.scrapper.service.interfaces.LinkService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JpaLinkService implements LinkService {
    private final LinkRepository linkRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public JpaLinkService(LinkRepository linkRepository, ChatRepository chatRepository) {
        this.linkRepository = linkRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public void add(long tgChatId, long linkId, URI url) {
        var l = linkRepository.findById(linkId);
        var newLink = l.orElseGet(() -> new edu.java.scrapper.entity.Link(linkId, url.toString()));
        newLink.getFollowingChats().add(new Chat(tgChatId, "default"));
        if (l.isEmpty()) {
            linkRepository.save(newLink);
        }
    }

    @Override
    @Transactional
    public void remove(long tgChatId, long linkId) {
        var chat = chatRepository.findById(tgChatId);
        chat.ifPresent(value -> value.getFollowingLinks().remove(linkRepository.findById(linkId).orElse(null)));
    }

    @Override
    @Transactional
    public List<Link> listAll(long tgChatId) {
        return linkRepository
            .findAll()
            .stream()
            .filter(link -> link
                .getFollowingChats()
                .stream()
                .anyMatch(chat -> chat.getChatId() == tgChatId))
            .map(link -> new Link(link.getId(), link.getName()))
            .toList();
    }
}
