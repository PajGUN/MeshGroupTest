package ru.sunlab.meshgrouptest.dto.error;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ErrorDtoOut {

    private String msg;
    private Timestamp created;
}
