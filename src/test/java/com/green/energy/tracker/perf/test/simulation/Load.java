package com.green.energy.tracker.perf.test.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import com.green.energy.tracker.perf.test.util.Properties;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;

public class Load extends SingleUser {

    @Override
    protected SetUp doSetUp() {
        ScenarioBuilder scenario = serviceType.scenario();
        return setUp(scenario.injectClosed(
                constantConcurrentUsers(Properties.envOrElse("LOAD_USERS", 70))
                        .during(Duration.ofMinutes(Properties.envOrElse("LOAD_DURATION_MINUTES", 60))))
        );
    }
}
