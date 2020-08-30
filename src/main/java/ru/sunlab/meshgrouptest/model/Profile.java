package ru.sunlab.meshgrouptest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id; //int согласно спецификации!

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "created", nullable = false)
    private Timestamp created;
}
