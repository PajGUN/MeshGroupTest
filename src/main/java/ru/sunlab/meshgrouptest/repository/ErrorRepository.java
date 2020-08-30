package ru.sunlab.meshgrouptest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sunlab.meshgrouptest.model.ErrorRest;

@Repository
public interface ErrorRepository extends CrudRepository<ErrorRest, Long> {

    ErrorRest findTopByOrderByIdDesc();

}
