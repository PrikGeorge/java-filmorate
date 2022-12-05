package ru.yandex.practicum.filmorate.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 31.10.2022
 */

public enum GenerateIdentifier {

    INSTANCE;

    private final Map<Class<?>, Long> identifier = new HashMap<>();

    public long generateId(Class<?> clazz) {
        if (identifier.containsKey(clazz)) {
            long id = identifier.get(clazz);
            identifier.put(clazz, ++id);
            return id;

        } else {
            identifier.put(clazz, (long) 1);
            return 1;
        }
    }

}
