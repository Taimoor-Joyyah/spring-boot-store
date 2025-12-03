package com.codewithmosh.store.users;

import lombok.Data;

/**
 * DTO for {@link User}
 */
@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
