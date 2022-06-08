package com.swm.baseframework.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        // setKeySerializer/setValueSerializer 를 StringRedisSerializer으로 사용하면
        // redis-cli에서 데이터 직렬화/역직렬화할 때 알아 볼 수 있는 형태로 출력이 됨
        // 혹은 redisTemplate를 주입 받을 때 StringRedisTemplate로 주입받아도 똑같이 동작함
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
