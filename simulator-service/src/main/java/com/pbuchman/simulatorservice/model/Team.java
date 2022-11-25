package com.pbuchman.simulatorservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(schema = "simulator", name = "teams")
public class Team {

    @Id
    private Integer id;

    @NotBlank
    @NotNull
    String name;

    @NotBlank @NotNull
    String group;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer seed;
}
