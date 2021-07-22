package com.edytagodniak.releasemanager.system;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ServiceVersion {
    private String serviceName;
    private int serviceVersion;
}
