package org.nsu.fit.services.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanPojo {
    @JsonProperty("id")
    public UUID id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("details")
    public String details;

    @JsonProperty("fee")
    public int fee;

    public PlanPojo(UUID id, String name, String details, int fee) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.fee = fee;
    }

    public PlanPojo() {
    }
}