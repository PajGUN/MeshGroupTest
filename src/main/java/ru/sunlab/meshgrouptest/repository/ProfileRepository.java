package ru.sunlab.meshgrouptest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sunlab.meshgrouptest.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByEmailIgnoreCase(String email);

    Profile findTopByOrderByIdDesc();

}
