package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.AssetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private List<Asset> assets =new ArrayList<>();

    @Autowired
     private AssetDao dao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Asset asset) throws IOException {
        return dao.create(asset);
    }

    @RequestMapping(value = "/{assetId}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String assetId) throws IOException {
        return dao.delete(assetId);
    }

    @RequestMapping(value = "/{assetId}/assign/{ownerId}", method = RequestMethod.POST)
    public boolean assign(@RequestBody Asset asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
        //date of assignment to be mentioned
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Asset> getAll() throws IOException {
       return dao.getAll();
    }
    
    @RequestMapping(value="/{assetId}", method = {RequestMethod.GET})
    public Asset getById(@PathVariable String assetId) throws IOException {
        return dao.getById(assetId);
    }
    
    @RequestMapping(value="/type/{assetType}", method = {RequestMethod.GET})
    public List<Asset> getByType(@PathVariable String assetType) throws IOException {
        return dao.getByType(assetType);
    }

    @RequestMapping(value = "/{assetId}/unassign/{ownerId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String assetId, String employeeId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }

}
