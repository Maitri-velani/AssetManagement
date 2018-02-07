package com.brevitaz.AssetManagement.controller;

import org.springframework.web.bind.annotation.*;

import com.brevitaz.AssetManagement.model.Assets;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private List<Assets> assets =new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Assets assets)
    {
        System.out.println("Asset is created");
       // assets.add(asset);
        return true;
    }

    @RequestMapping(value = "/{assetId}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String assetId)
    {
        System.out.println("Asset is deleted");
        return true;
    }

    @RequestMapping(value = "/{assetId}/assign/{ownerId}", method = RequestMethod.POST)
    public boolean assign(@RequestBody Assets asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
        //date of assignment to be mentioned
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Assets> getAll()
    {
        System.out.println("Get assets");
        return assets;
    }
    
    @RequestMapping(value="/{assetId}", method = {RequestMethod.GET})
    public Assets getById(@PathVariable String assetId)
    {
        System.out.println("Get assets with id: " + assetId);
        return null;
    }
    
    @RequestMapping(value="/type/{assetType}", method = {RequestMethod.GET})
    public List<Assets> getByType(@PathVariable String assetType)
    {
        System.out.println("Get assets of type: " + assetType);
        return assets;
    }
  
    @RequestMapping(value="/owner/{onwnerId}", method = {RequestMethod.GET})
    public List<Assets> getByOwner(@PathVariable String ownerId)
    {
        System.out.println("Get assets with owner: " + ownerId);
        return assets;
    }
    
    @RequestMapping(value = "/{assetId}/unassign/{ownerId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String assetId, String employeeId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }
    @RequestMapping(value="/{ownerId}/request", method=RequestMethod.POST)
    public boolean request(@RequestBody Assets asset, @PathVariable String employeeId)
    {
    	System.out.println("Request asset");
    	return true;
    }
}
