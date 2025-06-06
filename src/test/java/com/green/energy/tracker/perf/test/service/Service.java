package com.green.energy.tracker.perf.test.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Service {
    USER_MANAGEMENT("user",1,"user-management"),
    SITE_SENSOR_MANAGEMENT("site-sensor",1,"site-sensor-management"),
    ALL("all",1,"");

    private final String name;
    private final Integer version;
    private final String service;
}
