package com.codewithmosh.store.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "active_tokens")
@AllArgsConstructor
@NoArgsConstructor
public class ActiveToken {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "token")
    private String token;
}
