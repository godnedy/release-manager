package com.edytagodniak.releasemanager.latestversion;

import org.springframework.data.repository.CrudRepository;

public interface LatestVersionRepository extends CrudRepository<LatestVersion, Integer> {
}
