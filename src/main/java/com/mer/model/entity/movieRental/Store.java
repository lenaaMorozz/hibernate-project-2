package com.mer.model.entity.movieRental;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", columnDefinition = "tinyint")
    private Integer id;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "manager_staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

}
