package com.mer.model.entity.movieRental;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", columnDefinition = "tinyint")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Lob
    @Column(name = "picture", columnDefinition = "BLOB")
    private byte[] picture;

    @Column(name = "email", length = 50)
    private String email;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "active", columnDefinition = "BIT DEFAULT 1", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive = true;

    @Column(name = "username", length = 16)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}

