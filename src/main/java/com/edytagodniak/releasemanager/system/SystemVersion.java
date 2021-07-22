package com.edytagodniak.releasemanager.system;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Map;

@EqualsAndHashCode
@RedisHash
@Getter
@AllArgsConstructor
class SystemVersion {
    @Id
    private final int id;
    private final Map<String, ServiceVersion> serviceVersions;
}
