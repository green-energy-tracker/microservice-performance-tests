package com.green.energy.tracker.perf.test.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import com.green.energy.tracker.perf.test.util.Properties;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;

public class Stress extends SingleUser {

    @Override
    protected SetUp doSetUp() {
        ScenarioBuilder scenario = serviceType.scenario();
        return setUp(scenario.injectClosed(
                rampConcurrentUsers(Properties.envOrElse("STRESS_STARTING_USERS", 1)).to(Properties.envOrElse("STRESS_ENDING_USERS", 50))
                        .during(Duration.ofMinutes(Properties.envOrElse("STRESS_DURATION_MINUTES", 60))))
        );
    }
}
