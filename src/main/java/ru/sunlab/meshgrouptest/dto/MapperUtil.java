package ru.sunlab.meshgrouptest.dto;

import ru.sunlab.meshgrouptest.dto.error.ErrorDtoOut;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoIn;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoOutGet;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoOutPost;
import ru.sunlab.meshgrouptest.model.ErrorRest;
import ru.sunlab.meshgrouptest.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    public static ProfileDtoOutPost mapToProfileDtoOutPost(Profile profile){
        ProfileDtoOutPost profileDtoOutPost = new ProfileDtoOutPost();
        profileDtoOutPost.setIdUser(profile.getId());
        return profileDtoOutPost;
    }

    public static Profile mapToProfile(ProfileDtoIn profileDtoIn) {
        Profile profile = new Profile();
        profile.setName(profileDtoIn.getName());
        profile.setEmail(profileDtoIn.getEmail());
        profile.setAge(profileDtoIn.getAge());
        return profile;
    }

    public static ProfileDtoOutGet mapToProfileDtoOutGet(Profile profile) {
        String name = profile.getName();
        profile.setName(name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase());

        ProfileDtoOutGet profileDtoOutGet = new ProfileDtoOutGet();
        profileDtoOutGet.setId(profile.getId());
        profileDtoOutGet.setName(profile.getName());
        profileDtoOutGet.setEmail(profile.getEmail());
        profileDtoOutGet.setAge(profile.getAge());
        profileDtoOutGet.setCreated(profile.getCreated());
        return profileDtoOutGet;
    }

    public static List<ProfileDtoOutGet> mapToListProfileDtoOutGet(List<Profile> profiles) {
        List<ProfileDtoOutGet> profilesDto = new ArrayList<>();
        for (Profile profile : profiles) {
            profilesDto.add(mapToProfileDtoOutGet(profile));
        }
        return profilesDto;
    }

    public static ErrorDtoOut mapToErrorDtoOut(ErrorRest errorRest) {
        ErrorDtoOut errorDtoOut = new ErrorDtoOut();
        errorDtoOut.setMsg(errorRest.getMsg());
        errorDtoOut.setCreated(errorRest.getCreated());
        return errorDtoOut;
    }
}
