package com.mer.model.movieRental;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "address_id")
        private Short id;

        @Column(name = "address", length = 50, nullable = false)
        private String address;

        @Column(name = "address2", length = 50)
        private String address2;

        @Column(name = "district", length = 20, nullable = false)
        private String district;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "city_id")
        private City city;

        @Column(name = "postal_code", length = 10, nullable = false)
        private String postalCode;

        @Column(name = "phone", length = 20, nullable = false)
        private String phone;

        @Column(name = "last_update")
        @UpdateTimestamp
        private LocalDateTime lastUpdate;

}
