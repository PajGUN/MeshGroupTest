package ru.sunlab.meshgrouptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sunlab.meshgrouptest.exception.NoRecordsException;
import ru.sunlab.meshgrouptest.model.ErrorRest;
import ru.sunlab.meshgrouptest.repository.ErrorRepository;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;
    @Autowired
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Transactional
    public ErrorRest getLastError() {
        ErrorRest lastError = errorRepository.findTopByOrderByIdDesc();
        if (lastError == null) {
            String msg = "No records!";
            throw new NoRecordsException(msg);
        }
        return lastError;
    }

    @Transactional
    public void save(String msg){
        errorRepository.save(new ErrorRest(msg, new Timestamp(new Date().getTime())));
    }
}
