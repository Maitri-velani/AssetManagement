package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Request;

import java.io.IOException;
import java.util.List;

public interface RequestDao {

    public boolean create(Request request, String ownerId);
    public List<Request> getAll();
    public boolean delete(String id);
    public Request getById(String id);
}
