package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.AssetDao;
import com.brevitaz.AssetManagement.model.AssetOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brevitaz.AssetManagement.model.Asset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/asset")
public class AssetController {

   @Autowired
     private AssetDao assetDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Asset asset){
        return assetDao.create(asset);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id){
        return assetDao.delete(id);
    }

    @RequestMapping(value = "/{id}/owner/{ownerId}/assign", method = RequestMethod.POST)
    public boolean assign(@RequestBody Asset asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
        //date of assignment to be mentioned
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Asset> getAll(){
       return assetDao.getAll();
    }
    
    @RequestMapping(value="/{id}", method = {RequestMethod.GET})
    public Asset getById(@PathVariable String id){
        return assetDao.getById(id);
    }
    
    @RequestMapping(value="/type/{type}", method = {RequestMethod.GET})
    public List<Asset> getByType(@PathVariable String type) {
        return assetDao.getByType(type);
    }

    @RequestMapping(value = "/{id}/unassign/{ownerId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String id, String ownerId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }




    @RequestMapping(value = "/get-all-owner",method = {RequestMethod.GET})
    public List<AssetOwner> getAllOwner(){
        return assetDao.getAllOwner();
    }

    @RequestMapping(value="/name/{firstName}", method = {RequestMethod.GET})
    public List<AssetOwner> getOwnerByName(@PathVariable String firstName){
        return assetDao.getOwnerByName(firstName);
    }

    @RequestMapping(value="/id/{id}", method = {RequestMethod.GET})
    public AssetOwner getByOwnerId(@PathVariable String id){
        return assetDao.getOwnerById(id);
    }
}