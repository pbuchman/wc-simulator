package com.pbuchman.simulatorservice.probabilities.client;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
class ProbabilitiesResponseRepository {
    private final ReactiveRedisOperations<String, ProbabilitiesResponse> reactiveRedisOperations;

    public Mono<ProbabilitiesResponse> findById(String id) {
        return reactiveRedisOperations
                .opsForValue()
                .get(id);
    }

    public Mono<Boolean> save(String key, ProbabilitiesResponse probabilities) {
        return reactiveRedisOperations
                .opsForValue()
                .set(key, probabilities);
    }
}