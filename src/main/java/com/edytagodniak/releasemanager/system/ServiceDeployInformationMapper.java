package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.api.ServiceDeployInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceDeployInformationMapper {

    public ServiceVersion map(ServiceDeployInformation serviceDeployInformation) {
        return new ServiceVersion(serviceDeployInformation.getName(), serviceDeployInformation.getVersion());
    }

    public ServiceDeployInformation map(ServiceVersion serviceVersion) {
        return new ServiceDeployInformation(serviceVersion.getServiceName(), serviceVersion.getServiceVersion());
    }
}
