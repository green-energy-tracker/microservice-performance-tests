package com.green.energy.tracker.perf.test.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import com.green.energy.tracker.perf.test.service.factory.ServiceType;
import com.green.energy.tracker.perf.test.service.factory.ServiceTypeFactory;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class SingleUser extends Simulation {
    protected final ServiceType serviceType;

    public SingleUser() {
        System.out.println("SERVICE_TYPE: " + System.getProperty("SERVICE_TYPE"));
        System.out.println("APP_URL: " + System.getProperty("APP_URL"));
        serviceType = ServiceTypeFactory.getServiceType(System.getProperty("SERVICE_TYPE"));
        doSetUp().protocols(http.contentTypeHeader("application/json; charset=UTF-8").baseUrl(System.getProperty("APP_URL")));
    }

    protected SetUp doSetUp() {
        ScenarioBuilder scenario = serviceType.scenario();
        return setUp(scenario.injectOpen(atOnceUsers(1)));
    }
}
