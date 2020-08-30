package ru.sunlab.meshgrouptest.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProfileDtoOutGet {

    private int id;

    private String name;

    private String email;

    private int age;

    private Timestamp created;
}
