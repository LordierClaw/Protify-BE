package com.proptit.protify_be.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NonNull
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Date createdDate = new Date();

    private Date lastOnlineDate;
    private String phoneNumber;
    private String profilePicture;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}