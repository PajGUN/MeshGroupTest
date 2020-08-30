package ru.sunlab.meshgrouptest.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProfileDtoOutError {

    private String msg;
    private Timestamp created;
}
