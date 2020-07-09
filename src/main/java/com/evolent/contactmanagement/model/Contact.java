package com.evolent.contactmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CONTACTS")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
