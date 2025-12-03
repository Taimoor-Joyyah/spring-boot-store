package com.codewithmosh.store.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class WebhookRequest {
    private final Map<String, String> headers;
    private final String payload;

    String getHeader(String name) {
        return headers.get(name);
    }
}
