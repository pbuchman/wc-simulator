package com.pbuchman.simulatorservice.probabilities.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    ReactiveRedisOperations<String, ProbabilitiesResponse> redisOperations(ReactiveRedisConnectionFactory factory) {
        var serializer = new Jackson2JsonRedisSerializer<>(ProbabilitiesResponse.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, ProbabilitiesResponse> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, ProbabilitiesResponse> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
