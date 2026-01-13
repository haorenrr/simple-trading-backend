package org.example.mylearn.tradingengine.asset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.mylearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/asset", produces = MediaType.APPLICATION_JSON_VALUE)
class AssetController {

    @Autowired
    AssetService assetService;
    Logger logger = LoggerFactory.getLogger(AssetController.class);
    static final Gson GSON = new GsonBuilder().create();

    @GetMapping("/get")
    public Result<AssetEntity> getAsset(
            @RequestParam(name = "uid") String uid){
        return assetService.getAssetByUidAndType(uid, AssetType.USD);
    }

    @GetMapping("/list")
    public Result<List<AssetEntity>> getAssetList(){
        var listResult = assetService.getAllAssets();
        String jsonAsset = GSON.toJson(listResult);
        logger.debug("getAssetList():{} ", jsonAsset);
        return listResult;
    }

    @GetMapping("/add")
    public Result<AssetEntity> addAsset(
            @RequestParam(name = "uid") String uid,
            @RequestParam(name = "type") AssetType type,
            @RequestParam(required = false) BigDecimal amount){

        var newAssetRlt= assetService.addNewAsset(uid, type);
        if(!newAssetRlt.isSuccess() || amount == null){
            return newAssetRlt;
        }
        var rechargeRlt = assetService.recharge(uid, type, amount);
        String msg = GSON.toJson(rechargeRlt);
        logger.debug("addAsset():{} ", msg);
        return new Result<>(rechargeRlt.isSuccess(), newAssetRlt.getData(),rechargeRlt.getErrorCode(), rechargeRlt.getMessage());
    }
}