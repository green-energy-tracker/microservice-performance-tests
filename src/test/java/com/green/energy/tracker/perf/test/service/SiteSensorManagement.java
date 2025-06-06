package com.green.energy.tracker.perf.test.service;

import com.green.energy.tracker.perf.test.service.factory.ServiceType;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

@SuperBuilder
public class SiteSensorManagement extends ServiceType {

    public SiteSensorManagement(Service service) {
        super(service);
    }

    @Getter
    @RequiredArgsConstructor
    enum OperationSite {
        FIND_BY_NAME("site/findByName");
        private final String value;
    }
    @Getter
    @RequiredArgsConstructor
    enum OperationSensor {
        FIND_BY_CODE("sensor/findByCode");
        private final String value;
    }

    @Override
    public ScenarioBuilder scenario() {
        return CoreDsl.scenario("SiteSensorManagement").exec(findByName(),findByCode());
    }

    public ChainBuilder chain() {
        return exec(findByName(),findByCode());
    }

    private HttpRequestActionBuilder findByName() {
        String url = buildUrl(OperationSite.FIND_BY_NAME.getValue());
        return http("GET " + url)
                .get(url)
                .queryParam("name", "Ecosol2")
                .check(status().is(200));
    }

    private HttpRequestActionBuilder findByCode() {
        String url = buildUrl(OperationSensor.FIND_BY_CODE.getValue());
        return http("GET " + url)
                .get(url)
                .queryParam("code", "HTRYR6548")
                .check(status().is(200));
    }

}
