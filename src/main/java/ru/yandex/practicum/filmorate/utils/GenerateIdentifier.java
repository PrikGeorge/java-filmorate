package ru.yandex.practicum.filmorate.utils;

import java.util.HashMap;
import java.util.Map;

public enum GenerateIdentifier {

    INSTANCE;

    private final Map<Class<?>, Integer> identifier = new HashMap<>();

    public int generateId(Class<?> clazz) {
        if (identifier.containsKey(clazz)) {
            int id = identifier.get(clazz);
            identifier.put(clazz, ++id);
            return id;

        } else {
            identifier.put(clazz, 1);
            return 1;
        }
    }

}
