package com.edytagodniak.releasemanager.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ServiceDeployInformation {

    @NotEmpty(message = "name cannot be empty")
    private String name;
    @Min(value = 0, message = "version must be bigger than 0")
    private int version;
}
