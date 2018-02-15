package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.RequestDao;
import com.brevitaz.AssetManagement.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/request")
public class RequestController {

    @Autowired
    private RequestDao requestDao;

    @RequestMapping(value = "/{ownerId}",method = RequestMethod.POST)
    public boolean create(@RequestBody Request request, @PathVariable String ownerId) throws IOException {
        return requestDao.insert(request,ownerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Request> getAll() throws IOException {
        return requestDao.getAll ();

    }

    @RequestMapping(value = "/{id}/approve",method = RequestMethod.POST)
    public boolean approve(@PathVariable String id)
    {
        return false;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE) //only to test
    public boolean delete(@PathVariable String id) throws IOException {
        return requestDao.delete(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET) //only to test
    public Request getById(@PathVariable String id) throws IOException {
        return requestDao.getById(id);
    }

}
