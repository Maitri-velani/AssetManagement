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
    public boolean createAsset(@RequestBody Assets assets)
    {
        System.out.println("Asset is created");
       // assets.add(asset);
        return true;
    }

    @RequestMapping(value = "/{assetId}",method = RequestMethod.DELETE)
    public boolean deleteAsset(@PathVariable String assetId)
    {
        System.out.println("Asset is deleted");
        return true;
    }

    @RequestMapping(value = "/{assetId}/assign/{employeeId}", method = RequestMethod.POST)
    public boolean assignAsset(@RequestBody Assets asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Assets> displayAssets()
    {
        System.out.println("Get assets");
        return assets;
    }

    @RequestMapping(value = "/{assetId}/unassign/{employeeId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String assetId, String employeeId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }
    @RequestMapping(value="/{employeeId}/requests", method=RequestMethod.POST)
    public boolean requestAsset(@RequestBody Assets asset, @PathVariable String employeeId)
    {
    	System.out.println("Request asset");
    	return true;
    }
}
