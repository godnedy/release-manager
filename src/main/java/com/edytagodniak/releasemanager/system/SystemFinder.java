package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.api.ServiceDeployInformation;
import com.edytagodniak.releasemanager.api.SystemVersionNotFoundException;
import com.edytagodniak.releasemanager.latestversion.LatestVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
class SystemFinder {

    private final SystemVersionRepository systemVersionRepository;
    private final LatestVersionService latestVersionService;

    public List<ServiceDeployInformation> getServicesVersionsForSystemVersion(int systemVersion) {

        SystemVersion system = systemVersionRepository.findById(systemVersion).orElseThrow(SystemVersionNotFoundException::new);

        List<ServiceDeployInformation> collect = system.getServiceVersions().values().stream()
                .map(ServiceDeployInformationMapper::map)
                .collect(Collectors.toList());

        return collect;
    }

    public Optional<SystemVersion> findLatestSystemVersion() {
        Optional<Integer> latestVersion = latestVersionService.findLatestSystemVersion();
        return latestVersion.flatMap(systemVersionRepository::findById);
    }
}
