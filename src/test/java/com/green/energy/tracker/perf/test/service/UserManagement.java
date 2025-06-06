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
public class UserManagement extends ServiceType {

    public UserManagement(Service service) {
        super(service);
    }

    @Getter
    @RequiredArgsConstructor
    enum Operation {
        FIND_BY_USERNAME("findByUsername"),
        FIND_BY_ID("findById"),;
        private final String value;
    }

    @Override
    public ScenarioBuilder scenario() {
        return CoreDsl.scenario("UserManagement").exec(findByUsername(),findById());
    }

    public ChainBuilder chain() {
        return exec(findByUsername(),findById());
    }

    private HttpRequestActionBuilder findByUsername() {
        String url = buildUrl(Operation.FIND_BY_USERNAME.getValue());
        return http("GET " + url)
                .get(url)
                .queryParam("username", "ADMIN")
                .check(status().is(200));
    }

    private HttpRequestActionBuilder findById() {
        String url = buildUrl(Operation.FIND_BY_ID.getValue());
        return http("GET " + url)
                .get(url)
                .queryParam("id", 1)
                .check(status().is(200));
    }

}
