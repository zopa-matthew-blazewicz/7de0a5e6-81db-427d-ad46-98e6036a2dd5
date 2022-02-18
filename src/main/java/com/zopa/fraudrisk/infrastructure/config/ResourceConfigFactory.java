package com.zopa.fraudrisk.infrastructure.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import java.util.HashMap;
import java.util.Map;

public class ResourceConfigFactory {

    private static final String BASE_PACKAGE = "com.zopa.fraudrisk.ui.rest";

    public static ResourceConfig baseConfig() {
        ResourceConfig config = new ResourceConfig().packages(BASE_PACKAGE);
        Map<String, Object> map = new HashMap<>(config.getProperties());
        map.put(ServerProperties.WADL_FEATURE_DISABLE, true);
        config.setProperties(map);
        return config;
    }
}
