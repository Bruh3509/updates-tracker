package edu.java.scrapper.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    @ManyToMany(mappedBy = "followingLinks",
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Chat> followingChats = new ArrayList<>();

    public Link() {
    }

    public Link(Long id, String name, Long curTime, OffsetDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.curTime = curTime;
        this.lastUpdate = lastUpdate;
    }
}
