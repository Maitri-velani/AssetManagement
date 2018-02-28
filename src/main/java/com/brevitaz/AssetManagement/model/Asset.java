package com.brevitaz.AssetManagement.model;

import java.util.Objects;

public class Asset {
    private String id;
    private String name;
    private String type;
    private String productId;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", productId='" + productId + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return status == asset.status &&
                Objects.equals(id, asset.id) &&
                Objects.equals(name, asset.name) &&
                Objects.equals(type, asset.type) &&
                Objects.equals(productId, asset.productId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, productId, status);
    }
}

