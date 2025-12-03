package com.codewithmosh.store.auth;

import org.springframework.data.repository.CrudRepository;

public interface ActiveTokenRepository extends CrudRepository<ActiveToken, Long> {
}
