package com.green.energy.tracker.perf.test.service;

import com.green.energy.tracker.perf.test.service.factory.ServiceType;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import lombok.experimental.SuperBuilder;


@SuperBuilder
public class AllService extends ServiceType {

    public AllService(Service service) {
        super(service);
    }

    @Override
    public ScenarioBuilder scenario() {
        return CoreDsl.scenario("CombinedScenario")
                .exec(UserManagement.builder().service(service).build().chain())
                .exec(SiteSensorManagement.builder().service(service).build().chain());
    }
}
