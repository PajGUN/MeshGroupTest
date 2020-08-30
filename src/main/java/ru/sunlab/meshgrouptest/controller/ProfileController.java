package ru.sunlab.meshgrouptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sunlab.meshgrouptest.dto.*;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoIn;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoInEmail;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoOutGet;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoOutPost;
import ru.sunlab.meshgrouptest.model.Profile;
import ru.sunlab.meshgrouptest.service.ProfileService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProfileController {

    private final ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles/set")
    public ResponseEntity<ProfileDtoOutPost> createProfile(@Valid @RequestBody ProfileDtoIn profileDtoIn){
        Profile profile = profileService.save(MapperUtil.mapToProfile(profileDtoIn));
        return ResponseEntity.ok(MapperUtil.mapToProfileDtoOutPost(profile));
    }

    @GetMapping("/profiles/last")
    public ResponseEntity<ProfileDtoOutGet> getLast(){
        Profile profile = profileService.getLastRecord();
        return ResponseEntity.ok(MapperUtil.mapToProfileDtoOutGet(profile));
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDtoOutGet>> getAllProfiles(){
        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(MapperUtil.mapToListProfileDtoOutGet(profiles));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<ProfileDtoOutGet> getProfileById(@PathVariable int id){
        Profile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(MapperUtil.mapToProfileDtoOutGet(profile));
    }

    @PostMapping("/profiles/get")
    public ResponseEntity<ProfileDtoOutGet> getProfileByEmail(@RequestBody ProfileDtoInEmail profileEmail){
        Profile profile = profileService.getProfileByEmail(profileEmail);
        return ResponseEntity.ok(MapperUtil.mapToProfileDtoOutGet(profile));
    }

    @GetMapping("/exit")
    public ResponseEntity<String> exit(){
        return ResponseEntity.noContent().build();
    }

}
