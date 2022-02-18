package com.zopa.fraudrisk.infrastructure.config;

import com.zopa.fraudrisk.core.FraudScoreRepository;
import com.zopa.fraudrisk.core.FraudScoreService;
import com.zopa.fraudrisk.infrastructure.fraudster.FraudsterClient;
import com.zopa.fraudrisk.infrastructure.fraudster.FraudsterScoreProvider;
import com.zopa.fraudrisk.infrastructure.steerclear.SteerClearClient;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class FraudScoreModule extends AbstractBinder {

    FraudScoreRepository fraudScoreRepository;
    FraudsterClient fraudsterClient;
    SteerClearClient steerClearClient;

    public FraudScoreModule(FraudScoreRepository fraudScoreCache,
                            FraudsterClient fraudsterClient,
                            SteerClearClient steerClearClient) {
        this.fraudScoreRepository = fraudScoreCache;
        this.fraudsterClient = fraudsterClient;
        this.steerClearClient = steerClearClient;
    }

    @Override
    protected void configure() {
        bind(new FraudScoreService(fraudScoreRepository, new FraudsterScoreProvider(fraudsterClient)))
            .to(FraudScoreService.class);
    }
}
