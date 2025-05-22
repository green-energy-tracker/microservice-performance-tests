package com.green.energy.tracker.perf.test.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import com.green.energy.tracker.perf.test.util.Properties;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;

public class Spike extends SingleUser {

    @Override
    protected SetUp doSetUp() {
        ScenarioBuilder scenario = serviceType.scenario();
        return setUp(scenario.injectClosed(
                rampConcurrentUsers(Properties.envOrElse("SPIKE_STARTING_USERS", 1)).to(Properties.envOrElse("SPIKE_USERS", 100))
                        .during(Duration.ofMinutes(Properties.envOrElse("SPIKE_DURATION1_MINUTES", 15))),
                constantConcurrentUsers(Properties.envOrElse("SPIKE_USERS", 100))
                        .during(Duration.ofMinutes(Properties.envOrElse("SPIKE_DURATION2_MINUTES", 10))),
                rampConcurrentUsers(Properties.envOrElse("SPIKE_USERS", 100)).to(Properties.envOrElse("SPIKE_ENDING_USERS", 1))
                        .during(Duration.ofMinutes(Properties.envOrElse("SPIKE_DURATION1_MINUTES", 15))))
        );
    }
}
