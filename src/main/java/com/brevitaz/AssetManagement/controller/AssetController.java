package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.AssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

   @Autowired
     private AssetDao assetDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Asset asset) throws IOException {
        return assetDao.create(asset);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id) throws IOException {
        return assetDao.delete(id);
    }

    @RequestMapping(value = "/{id}/assign/{ownerId}", method = RequestMethod.POST)
    public boolean assign(@RequestBody Asset asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
        //date of assignment to be mentioned
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Asset> getAll() throws IOException {
       return assetDao.getAll();
    }
    
    @RequestMapping(value="/{id}", method = {RequestMethod.GET})
    public Asset getById(@PathVariable String id) throws IOException {
        return assetDao.getById(id);
    }
    
    @RequestMapping(value="/type/{type}", method = {RequestMethod.GET})
    public List<Asset> getByType(@PathVariable String type) throws IOException {
        return assetDao.getByType(type);
    }

    @RequestMapping(value = "/{id}/unassign/{ownerId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String id, String ownerId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }
}