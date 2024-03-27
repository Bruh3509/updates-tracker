package edu.java.scrapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chat")
public class Chat {
    @Column(name = "chat_id") @Id
    private Long chatId;

    @Column(name = "user_name")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "chat_to_link",
        joinColumns = @JoinColumn(name = "chat_id"),
        inverseJoinColumns = @JoinColumn(name = "link_id")
    )
    private List<Link> followingLinks;

    public Chat() {
    }

    public Chat(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
}
