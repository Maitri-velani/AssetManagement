package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.List;

public interface AssetDao {
    public boolean create(Asset asset) throws IOException;
    public boolean delete(String assetId) throws IOException;
    public List<Asset> getAll() throws IOException;
    public Asset getById(String assetId) throws IOException;
    public List<Asset> getByType(String assetType) throws IOException;
    public List<Asset> getByOwner(String ownerId) throws IOException;
}
