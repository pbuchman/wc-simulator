package com.pbuchman.simulatorservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableJpaAuditing
@EnableJpaRepositories("com.pbuchman.simulatorservice.model")
@EntityScan("com.pbuchman.simulatorservice.model")
public class SimulatorServiceConfiguration {
}
