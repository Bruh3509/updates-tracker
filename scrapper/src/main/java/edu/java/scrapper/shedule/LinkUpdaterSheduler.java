package edu.java.scrapper.shedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class LinkUpdaterSheduler {
    @Scheduled(fixedRate = 5000)
    public void update() {
        log.info("Update call!");
    }
}
