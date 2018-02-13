package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.AssetOwner;
import java.io.IOException;
import java.util.List;

public interface AssetOwnerDao {
    public boolean create(AssetOwner assetOwner) throws IOException;
    public List<AssetOwner> getAll() throws IOException;
    public boolean update(AssetOwner assetOwner,String ownerId) throws IOException;
    public boolean delete(String ownerId) throws IOException;
    public List<AssetOwner> getOwnerByName(String ownerName) throws IOException;
    public AssetOwner getOwnerById(String ownerName) throws IOException;
}
