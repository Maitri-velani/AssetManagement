package com.brevitaz.AssetManagement.model;

import java.util.List;

public class Request {

    private String id;
    private String description;
    private boolean status;
    private Asset assets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Asset getAssets() {
        return assets;
    }

    public void setAssets(Asset assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assets=" + assets +
                '}';
    }


}
