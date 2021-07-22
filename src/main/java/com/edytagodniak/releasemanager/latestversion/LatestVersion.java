package com.edytagodniak.releasemanager.latestversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@AllArgsConstructor
@Getter
public class LatestVersion {
    @Id
    private final Integer id;
    private final Integer version;
}
