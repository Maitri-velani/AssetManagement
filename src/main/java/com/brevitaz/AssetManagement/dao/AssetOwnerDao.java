package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.AssetOwner;
import java.io.IOException;
import java.util.List;

public interface AssetOwnerDao {
    public boolean create(AssetOwner assetOwner) throws IOException;
    public List<AssetOwner> getAll() throws IOException;
    public boolean update(AssetOwner assetOwner,String id) throws IOException;
    public boolean delete(String id) throws IOException;
    public List<AssetOwner> getOwnerByName(String firstName) throws IOException;
    public AssetOwner getOwnerById(String id) throws IOException;

}
