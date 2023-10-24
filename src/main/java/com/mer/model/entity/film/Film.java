package com.mer.model.entity.film;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Entity
@Table(name = "film")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint")
    private Integer id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year", nullable = false, columnDefinition = "YEAR")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "DEFAULT 3")
    private byte rentalDuration = 3;

    @Column(name = "rental_rate", precision = 4, scale = 2, nullable = false, columnDefinition = "DECIMAL(4,2) DEFAULT 4.99")
    private BigDecimal rentalRate = new BigDecimal("4.99");

    @Column(name = "length", columnDefinition = "DEFAULT NULL")
    private short length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2, columnDefinition = "DEFAULT 19.99")
    private BigDecimal replacementCost = new BigDecimal("19.99");

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name = "rating", columnDefinition = "enum('G','PG','PG-13','R','NC-17')")
    private String rating = "R";

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Column(name = "special_features", columnDefinition = "set('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categorySet;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private List<Actor> actorSet;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    public Set<SpecialFeatures> getSpecialFeatures() {
        if (isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }
        String[] split = this.specialFeatures.split(",");
        return Arrays.stream(split)
                .map(SpecialFeatures::getFeaturesEnum)
                .collect(Collectors.toSet());
    }

    public void setSpecialFeatures(Set<SpecialFeatures> specialFeaturesSet) {
        this.specialFeatures = specialFeaturesSet.stream()
                .map(SpecialFeatures::getFeaturesString)
                .reduce((s, s2) -> s + "," + s2)
                .orElse(null);
    }

    public void setRating(Rating rating) {
        this.rating = rating.getRating();
    }

    public Rating getRating() {
        if(isNull(rating)) return null;
        return Rating.getRatingEnum(this.rating);
    }
}
