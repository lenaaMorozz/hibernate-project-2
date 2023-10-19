package com.mer.model.film;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpecialFeatures {
    Trailers("Trailers"),
    Commentaries("Commentaries"),
    DeletedScenes("Deleted scenes"),
    BehindTheScenes("Behind the scenes");

    private final String specialFeatures;

}
