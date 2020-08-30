package ru.sunlab.meshgrouptest.dto.profile;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProfileDtoInEmail {

    @NotEmpty
    private String email;
}
