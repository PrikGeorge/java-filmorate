DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS films_genres CASCADE;
DROP TABLE IF EXISTS user_friends CASCADE;
DROP TABLE IF EXISTS films_likes CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS MPA_ratings CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS reviews_likes CASCADE;
DROP TABLE IF EXISTS directors CASCADE;
DROP TABLE IF EXISTS films_directors CASCADE;
DROP TABLE IF EXISTS events CASCADE;

CREATE TABLE IF NOT EXISTS genres
(
    id   LONG auto_increment PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS MPA_ratings
(
    id   LONG auto_increment PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS films
(
    id           LONG auto_increment PRIMARY KEY,
    name         TEXT NOT NULL,
    description  TEXT         NOT NULL,
    release_date DATE         NOT NULL,
    duration     INTEGER,
    MPA_id       INTEGER REFERENCES MPA_ratings (id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS films_genres
(
    genre_id LONG REFERENCES genres (id) ON DELETE CASCADE,
    film_id  LONG REFERENCES films (id) ON DELETE CASCADE,
    PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS users
(
    id       LONG auto_increment PRIMARY KEY,
    login    TEXT NOT NULL,
    name     TEXT,
    email    TEXT NOT NULL,
    birthday DATE
);

CREATE UNIQUE INDEX IF NOT EXISTS users_email_uindex ON users (email);

CREATE UNIQUE INDEX IF NOT EXISTS users_login_uindex ON users (login);

CREATE TABLE IF NOT EXISTS user_friends
(
    user_id   LONG     NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    friend_id LONG     NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status    BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS films_likes
(
    film_id LONG REFERENCES films (id) ON DELETE CASCADE,
    user_id LONG REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, film_id)
);

CREATE TABLE IF NOT EXISTS reviews
(
    id            LONG auto_increment PRIMARY KEY,
    film_id       LONG REFERENCES films (id) ON DELETE CASCADE,
    user_id       LONG REFERENCES users (id) ON DELETE CASCADE,
    content       TEXT NOT NULL,
    is_positive    BOOLEAN NOT NULL DEFAULT 0,
    useful        INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS reviews_likes
(
    review_id     LONG REFERENCES reviews (id) ON DELETE CASCADE,
    user_id       LONG REFERENCES users (id) ON DELETE CASCADE,
    is_positive    BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY (user_id, review_id)
);

CREATE TABLE IF NOT EXISTS directors
(
    id LONG auto_increment PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS films_directors
(
    director_id LONG REFERENCES directors(id) ON DELETE CASCADE,
    film_id LONG REFERENCES films(id) ON DELETE CASCADE,
    PRIMARY KEY(film_id,director_id)
);

CREATE TABLE IF NOT EXISTS events
(
    event_id   LONG auto_increment PRIMARY KEY,
    user_id    LONG REFERENCES users(id) ON DELETE CASCADE,
    entity_id  LONG,
    event_type  TEXT,
    operation  TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
