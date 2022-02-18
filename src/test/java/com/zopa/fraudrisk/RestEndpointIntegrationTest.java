package com.zopa.fraudrisk;

import com.zopa.fraudrisk.infrastructure.cache.FraudScoreCache;
import com.zopa.fraudrisk.infrastructure.config.FraudScoreModule;
import com.zopa.fraudrisk.infrastructure.config.ResourceConfigFactory;
import com.zopa.fraudrisk.infrastructure.fraudster.FraudsterClientMock;
import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearClientMock;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class RestEndpointIntegrationTest {

    private final static int TEST_PORT = 18080;
    protected final static String TEST_URL = "http://localhost:" + TEST_PORT;

    private FraudRiskApp app;
    protected FraudsterClientMock fraudsterClientMock = new FraudsterClientMock();
    protected SteerClearClientMock steerClearClientMock = new SteerClearClientMock();

    @BeforeEach
    public void setup() throws Exception {
        ResourceConfig config = ResourceConfigFactory.baseConfig()
            .register(new FraudScoreModule(FraudScoreCache.getInstance(), fraudsterClientMock, steerClearClientMock));
        app = new FraudRiskApp(TEST_PORT, config);
        app.start();
    }

    @AfterEach
    public void tearDown() throws Exception {
        app.stop();
    }
}
