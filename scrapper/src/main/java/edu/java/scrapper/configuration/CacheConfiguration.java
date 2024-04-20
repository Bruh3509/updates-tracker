package edu.java.scrapper.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private static final Long CACHE_DURATION = 60L;

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(CACHE_DURATION, TimeUnit.MINUTES);
    }

    @Bean
    public CaffeineCacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("buckets");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
