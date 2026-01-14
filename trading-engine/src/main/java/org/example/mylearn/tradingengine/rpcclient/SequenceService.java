package org.example.mylearn.tradingengine.rpcclient;

import org.example.mylearn.common.rpc.SequenceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "sequenceClient", url="http://localhost:8081/")
public interface SequenceService extends SequenceApi {

}
