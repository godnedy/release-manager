package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.latestversion.LatestVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
class SystemHandler {

    private final LatestVersionService latestVersionService;
    private final SystemVersionRepository systemVersionRepository;

    public Integer addNewSystemVersion(Map<String ,ServiceVersion> serviceVersions) {
        Optional<Integer> latestSystemVersion = latestVersionService.findLatestSystemVersion();
        Integer newVersion = latestSystemVersion.map(integer -> integer + 1).orElse(1);
        SystemVersion incrementedSystemVersion = new SystemVersion(newVersion, serviceVersions);
        systemVersionRepository.save(incrementedSystemVersion);
        latestVersionService.updateLatestVersion(newVersion);
        return newVersion;
    }
}
