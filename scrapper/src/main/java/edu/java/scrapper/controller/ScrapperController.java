package edu.java.scrapper.controller;

import edu.java.scrapper.dto.scrapper.ScrapperDeleteResponse;
import edu.java.scrapper.dto.scrapper.ScrapperGetResponse;
import edu.java.scrapper.dto.scrapper.ScrapperPostRequest;
import edu.java.scrapper.dto.scrapper.ScrapperPostResponse;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ScrapperController {

    private static final String CACHE_NAME = "buckets";
    private static final Long TOKENS_1 = 20L;
    private static final Long TOKENS_2 = 10L;
    private static final Long DURATION = 20L;

    private final ChatService chatService;
    private final LinkService linkService;
    private final CaffeineCacheManager cacheManager;

    @Autowired
    public ScrapperController(
        ChatService chatService,
        LinkService linkService,
        CaffeineCacheManager cacheManager
    ) {
        this.chatService = chatService;
        this.linkService = linkService;
        this.cacheManager = cacheManager;
    }

    @PostMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> regChat(@PathVariable Long id) {
        Bucket bucket = getBucket(String.valueOf(id));

        if (bucket.tryConsume(1)) {
            chatService.register(id, "default"); // stub for user name
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        Bucket bucket = getBucket(String.valueOf(id));

        if (bucket.tryConsume(1)) {
            chatService.unregister(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<ScrapperGetResponse> getAllLinks(
        @RequestHeader(name = "Tg-Chat-Id") Long id
    ) {
        Bucket bucket = getBucket(String.valueOf(id));

        if (bucket.tryConsume(1)) {
            var links = linkService.listAll(id);
            return new ResponseEntity<>(
                new ScrapperGetResponse(links, links.size()),
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    @PostMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperPostResponse> addLink(
        @RequestHeader(name = "Tg-Chat-Id") Long id,
        @RequestBody ScrapperPostRequest request
    ) {
        Bucket bucket = getBucket(String.valueOf(id));

        if (bucket.tryConsume(1)) {
            log.info("Adding link");
            var link = request.link().url();
            linkService.add(id, link.toString().hashCode(), link);
            return new ResponseEntity<>(
                new ScrapperPostResponse(id, link),
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    @DeleteMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperDeleteResponse> deleteLink(
        @RequestHeader(name = "Tg-Chat-Id") Long id,
        @RequestBody ScrapperPostRequest link
    ) {
        Bucket bucket = getBucket(String.valueOf(id));

        if (bucket.tryConsume(1)) {
            linkService.remove(id, link.link().url().toString().hashCode());
            return new ResponseEntity<>(
                new ScrapperDeleteResponse(id, link.link().url()),
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }

    private Bucket bucketSupplier() {
        return Bucket.builder()
            .addLimit(Bandwidth.classic(TOKENS_1, Refill.intervally(TOKENS_1, Duration.ofMinutes(1))))
            .addLimit(Bandwidth.classic(TOKENS_2, Refill.intervally(TOKENS_2, Duration.ofSeconds(DURATION))))
            .build();
    }

    private Bucket getBucket(String ip) {
        var wrapper = cacheManager.getCache(CACHE_NAME).get(ip);
        Bucket bucket;
        if (wrapper != null) {
            bucket = (Bucket) wrapper.get();
        } else {
            bucket = bucketSupplier();
            cacheManager.getCache(CACHE_NAME).put(ip, bucket);
        }

        return bucket;
    }
}
