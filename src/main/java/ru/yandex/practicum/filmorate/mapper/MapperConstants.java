package ru.yandex.practicum.filmorate.mapper;

public enum MapperConstants {

    ID("id"),
    NAME("name"),
    DESCRIPTION("description"),
    RELEASE_DATE("release_date"),
    DURATION("duration"),
    MPA_ID("mpa_id"),
    MPA_NAME("mpa_name"),
    GENRE_ID("genre_id"),
    GENRE_NAME("genre_name"),
    EMAIL("email"),
    LOGIN("login"),
    BIRTHDAY("birthday");
    private final String lowerCaseName;

    MapperConstants(String lowerCaseName) {
        this.lowerCaseName = lowerCaseName;
    }

    public String lowerCaseName() {
        return lowerCaseName;
    }
}
