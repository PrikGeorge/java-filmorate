DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS films_genres CASCADE;
DROP TABLE IF EXISTS user_friends CASCADE;
DROP TABLE IF EXISTS films_likes CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS MPA_ratings CASCADE;

CREATE TABLE IF NOT EXISTS genres
(
    id   LONG auto_increment PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS MPA_ratings
(
    id   LONG auto_increment PRIMARY KEY,
    name VARCHAR(16) NOT NULL
);

CREATE TABLE IF NOT EXISTS films
(
    id           LONG auto_increment PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
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
    login    VARCHAR(25) NOT NULL,
    name     VARCHAR(255),
    email    VARCHAR(50) NOT NULL,
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
    content       VARCHAR(10000) NOT NULL,
    isPositive    BOOLEAN NOT NULL DEFAULT 0,
    useful        INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS review_likes
(
    review_id     LONG REFERENCES reviews (id) ON DELETE CASCADE,
    user_id       LONG REFERENCES users (id) ON DELETE CASCADE,
    isPositive    BOOLEAN NOT NULL DEFAULT 0,
    PRIMARY KEY (user_id, review_id)
);
