package com.brevitaz.AssetManagement.model;

import java.util.List;

public class Request {

    private String requestId;
    private String description;
    private boolean status;
    private List<Asset> asset;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Asset> getAsset() {
        return asset;
    }

    public void setAsset(List<Asset> asset) {
        this.asset = asset;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", asset=" + asset +
                '}';
    }
}
