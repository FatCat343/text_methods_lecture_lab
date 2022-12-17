package org.nsu.fit.services.fixtures;


import org.nsu.fit.services.rest.data.PlanPojo;

import java.util.UUID;

public class PlanFixtureBuilder {
    private UUID id = UUID.randomUUID();

    private String name = "name name name name";

    private String details = "details details details details";

    private int fee = 100;

    public PlanFixtureBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public PlanFixtureBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlanFixtureBuilder setDetails(String details) {
        this.details = details;
        return this;
    }

    public PlanFixtureBuilder setFee(int fee) {
        this.fee = fee;
        return this;
    }

    public PlanPojo build() {
        return new PlanPojo(id, name, details, fee);
    }
}
