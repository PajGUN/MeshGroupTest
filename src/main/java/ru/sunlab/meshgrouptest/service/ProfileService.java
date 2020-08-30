package ru.sunlab.meshgrouptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sunlab.meshgrouptest.dto.profile.ProfileDtoInEmail;
import ru.sunlab.meshgrouptest.exception.NoRecordsException;
import ru.sunlab.meshgrouptest.exception.ProfileExistsException;
import ru.sunlab.meshgrouptest.exception.ProfileNotFoundException;
import ru.sunlab.meshgrouptest.model.Profile;
import ru.sunlab.meshgrouptest.repository.ProfileRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ErrorService errorService;
    @Autowired
    public ProfileService(ProfileRepository profileRepository, ErrorService errorService) {
        this.profileRepository = profileRepository;
        this.errorService = errorService;
    }

    @Transactional(noRollbackFor = ProfileExistsException.class)
    public Profile save(Profile profile){
        Profile byEmailIgnoreCase = profileRepository.findByEmailIgnoreCase(profile.getEmail());
        if (byEmailIgnoreCase != null){
            String msg = "Profile is exist! Email - " + byEmailIgnoreCase.getEmail();
            errorService.save(msg);
            throw new ProfileExistsException(msg);
        }
        profile.setCreated(new Timestamp(new Date().getTime()));
        return profileRepository.save(profile);
    }

    @Transactional(noRollbackFor = NoRecordsException.class)
    public  Profile getLastRecord(){
        Profile profile = profileRepository.findTopByOrderByIdDesc();
        if (profile == null) {
            String msg = "No records!";
            errorService.save(msg);
            throw new NoRecordsException(msg);
        }
        return profile;
    }

    @Transactional(noRollbackFor = NoRecordsException.class)
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()){
            String msg = "No records!";
            errorService.save(msg);
            throw new NoRecordsException(msg);
        }
        return profiles;
    }

    @Transactional(noRollbackFor = ProfileNotFoundException.class)
    public Profile getProfileById(int id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()){
            String msg = "Profile not found! Id - " + id;
            errorService.save(msg);
            throw new ProfileNotFoundException(msg);
        }
        return profileOptional.get();

    }

    @Transactional(noRollbackFor = ProfileNotFoundException.class)
    public Profile getProfileByEmail(ProfileDtoInEmail profileEmail) {
        Profile profile = profileRepository.findByEmailIgnoreCase(profileEmail.getEmail());
        if (profile == null){
            String msg = "Profile by email not found! Email - " + profileEmail.getEmail();
            errorService.save(msg);
            throw new ProfileNotFoundException(msg);
        }
        return profile;
    }
}
