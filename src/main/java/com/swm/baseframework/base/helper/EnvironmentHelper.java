package com.swm.baseframework.base.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnvironmentHelper {

    private final Environment environment;

    public Boolean isTestProfiles() {
        return "test".equals(environment.getActiveProfiles()[0]);
    }
}
