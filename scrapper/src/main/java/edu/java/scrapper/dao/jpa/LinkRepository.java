package edu.java.scrapper.dao.jpa;

import edu.java.scrapper.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Modifying @Query(value = "UPDATE link SET last_update=?1 WHERE link_id=?2", nativeQuery = true)
    void updateModification(OffsetDateTime lastUpdate, Long id);

    @Modifying @Query(value = "UPDATE link SET last_check=?1 WHERE link_id=?2", nativeQuery = true)
    void updateCheck(Long time, Long id);
}
