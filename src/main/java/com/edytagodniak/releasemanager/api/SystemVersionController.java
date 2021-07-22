package com.edytagodniak.releasemanager.api;

import com.edytagodniak.releasemanager.system.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SystemVersionController {

    private final SystemService systemService;

    @GetMapping("/services")
    public List<ServiceDeployInformation> getServicesVersions(@RequestParam(name = "systemVersion") @Min(1) int systemVersion) {
        return systemService.getServicesVersionsForSystemVersion(systemVersion);
    }

    @PostMapping("/deploy")
    public int deployVersion(@Valid @RequestBody ServiceDeployInformation serviceDeployInformation) {
        return systemService.addDeployInformation(serviceDeployInformation);
    }

    @ExceptionHandler(SystemVersionNotFoundException.class)
    public ResponseEntity<Object> handleSystemVersionNotFoundException(SystemVersionNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

}
