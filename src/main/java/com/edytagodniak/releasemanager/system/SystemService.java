package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.api.ServiceDeployInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemService {

    private final SystemFinder systemFinder;

    private final SystemHandler systemHandler;

    @Transactional
    public int addDeployInformation(ServiceDeployInformation newServiceDeploy) {
        Optional<SystemVersion> latestSystemVersion = systemFinder.findLatestSystemVersion();

        if (latestSystemVersion.isPresent()) {
            if (hasServiceVersionChanged(newServiceDeploy, latestSystemVersion.get())) {
                Map<String, ServiceVersion> newMap = new HashMap<>(latestSystemVersion.get().getServiceVersions()); //TODO check if not changing
                newMap.put(newServiceDeploy.getName(), ServiceDeployInformationMapper.map(newServiceDeploy));
                return systemHandler.addNewSystemVersion(newMap);
            } else {
                return latestSystemVersion.get().getId();
            }
        } else {
            Map<String, ServiceVersion> serviceVersions = new HashMap<>();
            serviceVersions.put(newServiceDeploy.getName(), ServiceDeployInformationMapper.map(newServiceDeploy));
            return systemHandler.addNewSystemVersion(serviceVersions);
        }
    }


    @Transactional(readOnly = true)
    public List<ServiceDeployInformation> getServicesVersionsForSystemVersion(int systemVersion) {
        return systemFinder.getServicesVersionsForSystemVersion(systemVersion);
    }

    private boolean hasServiceVersionChanged(ServiceDeployInformation serviceDeployInformation, SystemVersion latestSystemVersion) {
        Integer latestServiceVersion = Optional.ofNullable(latestSystemVersion.getServiceVersions().get(serviceDeployInformation.getName()))
                .map(ServiceVersion::getServiceVersion)
                .orElse(null);
        return !Integer.valueOf(serviceDeployInformation.getVersion()).equals(latestServiceVersion);
    }

}
