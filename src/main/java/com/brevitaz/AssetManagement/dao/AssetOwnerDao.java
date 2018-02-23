package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.AssetOwner;
import java.io.IOException;
import java.util.List;

public interface AssetOwnerDao {
    public boolean create(AssetOwner assetOwner);
    public List<AssetOwner> getAll();
    public boolean update(AssetOwner assetOwner,String id);
    public boolean delete(String id);
    public List<AssetOwner> getOwnerByName(String firstName);
    public AssetOwner getOwnerById(String id);

}
