package com.green.energy.tracker.perf.test.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import com.green.energy.tracker.perf.test.util.Properties;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;

public class Baseline extends SingleUser {
    @Override
    protected SetUp doSetUp() {
        ScenarioBuilder scenario = serviceType.scenario();
        return setUp(scenario.injectClosed(
                constantConcurrentUsers(Properties.envOrElse("BASELINE_USERS", 1))
                        .during(Duration.ofMinutes(Properties.envOrElse("BASELINE_DURATION_MINUTES", 30)))
                )
        );
    }
}
