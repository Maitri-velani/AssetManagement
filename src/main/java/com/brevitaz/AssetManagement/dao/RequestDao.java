package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Request;

import java.io.IOException;
import java.util.List;

public interface RequestDao {

    public boolean create(Request request, String ownerId) throws IOException;
    public List<Request> getAll() throws IOException;
    public boolean delete(String id) throws IOException;
    public Request getById(String id) throws IOException;
}
