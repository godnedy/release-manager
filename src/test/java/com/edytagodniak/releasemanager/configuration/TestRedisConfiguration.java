package com.edytagodniak.releasemanager.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@TestConfiguration
public class TestRedisConfiguration {

    private static final int REDIS_TEST_PORT = 6388;

    private RedisServer redisServer;

    public TestRedisConfiguration() throws IOException {
        this.redisServer = new RedisServer(REDIS_TEST_PORT);
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
