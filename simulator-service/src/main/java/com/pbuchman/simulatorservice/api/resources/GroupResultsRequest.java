package com.pbuchman.simulatorservice.api.resources;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record GroupResultsRequest(
        @NotBlank @Pattern(regexp = "^[A-H]$", message = "Group should be a letter A-H") String group) {
}
