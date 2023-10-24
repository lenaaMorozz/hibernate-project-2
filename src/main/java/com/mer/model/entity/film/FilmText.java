package com.mer.model.entity.film;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "film_text")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmText {
    @Id
    @Column(name = "film_id", columnDefinition = "smallint")
    private Integer id;

    //TODO: @Id
    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    @Type(type = "text")
    private String description;

}
