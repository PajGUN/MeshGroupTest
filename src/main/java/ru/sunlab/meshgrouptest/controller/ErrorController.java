package ru.sunlab.meshgrouptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sunlab.meshgrouptest.dto.MapperUtil;
import ru.sunlab.meshgrouptest.dto.error.ErrorDtoOut;
import ru.sunlab.meshgrouptest.model.ErrorRest;
import ru.sunlab.meshgrouptest.service.ErrorService;

@RestController
public class ErrorController {

    private final ErrorService errorService;
    @Autowired
    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/error/last")
    public ResponseEntity<ErrorDtoOut> getLastError(){
        ErrorRest errorRest = errorService.getLastError();
        return ResponseEntity.ok(MapperUtil.mapToErrorDtoOut(errorRest));
    }
}
