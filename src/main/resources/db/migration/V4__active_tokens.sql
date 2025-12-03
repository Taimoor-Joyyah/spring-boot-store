CREATE TABLE active_tokens
(
    user_id BIGINT       NOT NULL PRIMARY KEY,
    token   VARCHAR(255) NOT NULL,
    CONSTRAINT active_tokens__user_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
