package org.example.mylearn.tradingengine.rpcclient;

import org.example.mylearn.common.rpc.SequenceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = SequenceApi.SERVER_NAME)
public interface SequenceFeignClient extends SequenceApi {

}
