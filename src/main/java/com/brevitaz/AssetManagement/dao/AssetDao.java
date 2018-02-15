package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.List;

public interface AssetDao {
    public boolean create(Asset asset) throws IOException;
    public boolean delete(String id) throws IOException;
    public List<Asset> getAll() throws IOException;
    public Asset getById(String id) throws IOException;
    public List<Asset> getByType(String type) throws IOException;

}
