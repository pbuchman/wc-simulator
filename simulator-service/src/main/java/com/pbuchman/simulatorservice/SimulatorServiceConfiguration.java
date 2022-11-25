package com.pbuchman.simulatorservice;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableR2dbcRepositories("com.pbuchman.simulatorservice.model")
@EntityScan("com.pbuchman.simulatorservice.model")
public class SimulatorServiceConfiguration {
}
