package com.mer.model.entity.film;

import lombok.AllArgsConstructor;

import static java.util.Objects.isNull;

@AllArgsConstructor
public enum SpecialFeatures {
    Trailers("Trailers"),
    Commentaries("Commentaries"),
    DeletedScenes("Deleted scenes"),
    BehindTheScenes("Behind the scenes");

    private final String specialFeatures;

    public String getFeaturesString() {
        return specialFeatures;
    }

    public static SpecialFeatures getFeaturesEnum(String s) {
        if (isNull(s) || s.isEmpty()) {
            return null;
        }
        switch (s) {
            case "Trailers" -> {
                return SpecialFeatures.Trailers;
            }
            case "Commentaries" -> {
                return SpecialFeatures.Commentaries;
            }
            case "Deleted scenes" -> {
                return SpecialFeatures.DeletedScenes;
            }
            case "Behind the scenes" -> {
                return SpecialFeatures.BehindTheScenes;
            }
        }
        return null;
    }

}
