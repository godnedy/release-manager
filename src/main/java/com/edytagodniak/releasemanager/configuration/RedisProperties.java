package com.edytagodniak.releasemanager.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RedisProperties {

    private final int redisPort;
    private final String redisHost;

    public RedisProperties(@Value("${spring.redis.port}") final int redisPort, @Value("${spring.redis.host}") final String redisHost) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }
}