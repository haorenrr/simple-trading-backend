package org.example.mylearn.tradingengine.asset;

import java.math.BigDecimal;

public class AssetEntity {
    Integer id; // ID标识
    String uid; //user id
    AssetType assetType; // APPL, or USD
    BigDecimal available; // 可用余额
    BigDecimal frozen; // 冻结额度

    public AssetEntity() {
        this(0, "000000", AssetType.INVALID, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public AssetEntity(Integer id, String uid, AssetType assetType, BigDecimal available, BigDecimal frozen) {
        this.id = id;
        this.uid = uid;
        this.assetType = assetType;
        this.available = available;
        this.frozen = frozen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }
}
