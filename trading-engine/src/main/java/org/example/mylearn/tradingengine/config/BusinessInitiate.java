package org.example.mylearn.tradingengine.config;

import org.example.mylearn.common.rpc.SequenceApi;
import org.example.mylearn.tradingengine.asset.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class BusinessInitiate {
    private final AssetService assetService;
    private final DiscoveryClient discoveryClient;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    Logger log = LoggerFactory.getLogger(BusinessInitiate.class);

    @Autowired
    public BusinessInitiate(AssetService assetService, DiscoveryClient discoveryClient) {
        this.assetService = assetService;
        this.discoveryClient = discoveryClient;
    }

    // Listen Registery's Hearbeat, and see if the requered service is available
    @EventListener
    public void onHeartbeat() {
        if(initialized.get()) return;

        var instances = discoveryClient.getInstances(SequenceApi.SERVER_NAME);
        if(instances == null || instances.isEmpty()){
            log.debug("service '{}' not found yet",  SequenceApi.SERVER_NAME);
            return;
        }
        if (initialized.compareAndSet(false, true)) {
            // run initialized now!
            log.info("Begin init Asset..");
            assetService.initAssetDB();
            log.info("initAssetDB() run over.");
        }
    }
}
