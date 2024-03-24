package edu.java.scrapper.repository;

import edu.java.scrapper.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query(value = "UPDATE link SET last_update=?1 WHERE link_id=?2", nativeQuery = true)
    void updateModification(OffsetDateTime lastUpdate, Long id);

    @Query(value = "UPDATE link SET last_check=?1 WHERE link_id=?2", nativeQuery = true)
    void updateCheck(Long time, Long id);
}
