package com.green.energy.tracker.perf.test.service.factory;

import com.green.energy.tracker.perf.test.service.Service;
import com.green.energy.tracker.perf.test.service.UserManagement;

import java.util.Arrays;

public final class ServiceTypeFactory {
    public static ServiceType getServiceType(String serviceType) {
        Service service = Arrays.stream(Service.values())
                .filter(microService->microService.getName().equals(serviceType))
                .findFirst().orElseThrow(()->new IllegalArgumentException("Unsupported service type: " + serviceType));
        ServiceType serviceTypeFactory = null;
        switch (service) {
            case USER_MANAGEMENT: serviceTypeFactory = new UserManagement(service);
        }
        return serviceTypeFactory;
    }
}
