package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Request;

import java.io.IOException;
import java.util.List;

public interface RequestDao {

    public boolean insert(Request request, String ownerId) throws IOException;
    public List<Request> getAll() throws IOException;
    public boolean delete(String requestId) throws IOException;
    public Request getById(String requestId) throws IOException;
}
