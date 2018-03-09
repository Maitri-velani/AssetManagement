package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;
import com.brevitaz.AssetManagement.model.AssetOwner;

import java.io.IOException;
import java.util.List;

public interface AssetDao {
    public boolean create(Asset asset);
    public boolean delete(String id);
    public List<Asset> getAll();
    public Asset getById(String id);
    public List<Asset> getByType(String type);
    public List<AssetOwner> getAllOwner();
    public List<AssetOwner> getOwnerByName(String firstName);
    public AssetOwner getOwnerById(String id);
}
