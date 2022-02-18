package com.zopa.fraudrisk;

import com.zopa.fraudrisk.infrastructure.cache.FraudScoreCache;
import com.zopa.fraudrisk.infrastructure.config.FraudScoreModule;
import com.zopa.fraudrisk.infrastructure.fraudster.FraudsterClientStub;
import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearClientStub;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

import static com.zopa.fraudrisk.infrastructure.config.ResourceConfigFactory.baseConfig;

public class FraudRiskApp {

    private static final String BASE_URL = "http://localhost:";
    private static final int DEFAULT_PORT = 8080;

    private final Server server;

    public static void main(String[] args) throws Exception {
        int port = DEFAULT_PORT;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new FraudRiskApp(port, defaultConfig()).start();
    }

    public FraudRiskApp(int port, ResourceConfig config) {
        server = JettyHttpContainerFactory.createServer(
            URI.create(BASE_URL + port),
            config
        );
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private static ResourceConfig defaultConfig() {
        return baseConfig().register(
            new FraudScoreModule(FraudScoreCache.getInstance(), new FraudsterClientStub(), new SteerClearClientStub()));
    }
}
