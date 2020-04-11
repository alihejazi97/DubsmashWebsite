package com.ali;

import com.google.common.cache.CacheBuilder;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import javax.persistence.Cache;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@SpringBootApplication
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@EnableAsync
public class Application {

    static SpringApplication springApplication;

    public static void main(String[] args) {
        springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }

//    @Bean(destroyMethod = "stop")
//    RedisServer getRedisServer(@Value("${spring.redis.port}") int redisPort) {
//        RedisServerBuilder serverBuilder = new RedisServerBuilder();
//        serverBuilder.port(redisPort);
//        RedisServer server = serverBuilder.build();
//        server.start();
//        return server;
//    }
}
