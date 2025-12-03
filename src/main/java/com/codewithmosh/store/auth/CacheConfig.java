package com.codewithmosh.store.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CacheConfig {
    @Bean
    public ActiveTokenCache activeTokenCache() {
        return new ActiveTokenCache();
    }
}
