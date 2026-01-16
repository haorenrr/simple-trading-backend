package org.example.mylearn.tradingengine.config;

import org.example.mylearn.tradingengine.asset.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class AppBizInit {
    private final AssetService assetService;
    private final DiscoveryClient discoveryClient;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    Logger log = LoggerFactory.getLogger(AppBizInit.class);

    @Autowired
    public AppBizInit(AssetService assetService, DiscoveryClient discoveryClient) {
        this.assetService = assetService;
        this.discoveryClient = discoveryClient;
    }

    // Listen Registery's Hearbeat, and see if the requered service is available
    @EventListener
    public void onHeartbeat(HeartbeatEvent event) {
        if(initialized.get()) return;

        var instances = discoveryClient.getInstances("sequence-engine");
        if(instances == null || instances.isEmpty()){
            log.debug("service 'sequence-engine' not found yet");
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
