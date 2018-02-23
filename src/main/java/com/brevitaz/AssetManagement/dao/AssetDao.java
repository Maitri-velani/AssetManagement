package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.List;

public interface AssetDao {
    public boolean create(Asset asset);
    public boolean delete(String id);
    public List<Asset> getAll();
    public Asset getById(String id);
    public List<Asset> getByType(String type);

}
