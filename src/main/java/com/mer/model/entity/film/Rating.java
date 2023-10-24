package com.mer.model.entity.film;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Objects.isNull;

@AllArgsConstructor
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String rating;

    public String getRating() {
        return rating;
    }

    public static Rating getRatingEnum(String value) {
        if (isNull(value) || value.isEmpty()) {
            return null;
        }
        Rating[] values = Rating.values();
        for (Rating rating : values) {
            if (rating.rating.equals(value)) {
                return rating;
            }
        }
        return null;
    }

}
