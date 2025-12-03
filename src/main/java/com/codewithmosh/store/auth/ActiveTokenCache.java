package com.codewithmosh.store.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActiveTokenCache {
    private final Map<Long, Jwt> activeTokens = new ConcurrentHashMap<>();

    public void put(Long userId, Jwt jwt) {
        activeTokens.put(userId, jwt);
    }

    public Jwt get(Long userId) {
        return activeTokens.get(userId);
    }

    public void remove(Long userId) {
        activeTokens.remove(userId);
    }
}
