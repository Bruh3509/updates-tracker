package edu.java.scrapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "link")
public class Link {
    @Column(name = "link_id") @Id
    private Long id;

    @Column(name = "link_name")
    private String name;

    @Column(name = "last_check")
    private Long curTime;

    @Column(name = "last_update")
    private OffsetDateTime lastUpdate;

    @ManyToMany(mappedBy = "followingLinks")
    private List<Chat> followingChats;

    public Link() {
    }

    public Link(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
