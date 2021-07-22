package com.edytagodniak.releasemanager.latestversion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LatestVersionService {

    private static final Integer ID_OF_ONLY_SINGLE_RECORD = 1;

    private final LatestVersionRepository latestVersionRepository;

    @Transactional
    public void updateLatestVersion(int versionToUpdate) {
        latestVersionRepository.save(new LatestVersion(ID_OF_ONLY_SINGLE_RECORD, versionToUpdate));
    }


    @Transactional
    public Optional<Integer> findLatestSystemVersion() {
        return latestVersionRepository.findById(ID_OF_ONLY_SINGLE_RECORD)
                .map(LatestVersion::getVersion);
    }

}
