package com.jvmup.nbbs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * 注解生成spring bean
 * ProjectName: NBBS
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 14:04
 **/
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("6379")
    private int port;
    @Value("3600")
    private int timeout;
    @Value("8")
    private int maxIdle;
    @Value("-1")
    private long maxWaitMillis;

    /**
     * 使用构建器生成lettuceClient的配置Bean
     * @return lettuceClientConfiguration Bean
     */
    @Bean("lettuceClientConfiguration")
    public LettuceClientConfiguration getLettuceClientConfiguration() {
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();

        return builder.build();
    }

    /**
     *
     * @return LettuceConnectionFactory
     */
    @Bean("lettuceConnectionFactory")
    public LettuceConnectionFactory getLettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPassword(RedisPassword.none());
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration, getLettuceClientConfiguration());
    }

    /**
     *
     * @return 返回redisCache的config 在这里可以实现自己的配置文件
     */
    @Bean("redisCacheConfiguration")
    public RedisCacheConfiguration getRedisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig();
    }

    @Bean("redisCacheWriter")
    public RedisCacheWriter getWriter(){
        return RedisCacheWriter.lockingRedisCacheWriter(getLettuceConnectionFactory());
    }
}

