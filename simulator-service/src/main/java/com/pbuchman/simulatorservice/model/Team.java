package com.pbuchman.simulatorservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "simulator", name = "teams")
public record Team(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id,
        @NotBlank @NotNull @Column(unique = true) String name
) {
}
