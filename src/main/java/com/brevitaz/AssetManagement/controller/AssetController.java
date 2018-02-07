package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.model.Asset;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private List<Asset> assets =new ArrayList<>();

    @RequestMapping(method = {RequestMethod.POST})
    public boolean create(@RequestBody Asset asset)
    {
        System.out.println("Asset is created");
       // assets.add(asset);
        return true;
    }

    @RequestMapping(value = "/{assetId}",method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String assetId)
    {
        System.out.println("Asset is deleted");
        return true;
    }

    @RequestMapping(value = "/{assetId}/assign/{employeeId}", method = {RequestMethod.POST})
    public Boolean assign(@RequestBody Asset asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<Asset> get()
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
    public boolean request(@RequestBody Asset asset, @PathVariable String employeeId)
    {
    	return true;
    }

    @RequestMapping(value = "/{assetType}",method = {RequestMethod.GET})
    public List<Asset> getByType()
    {
        System.out.println("Get assets");
        return assets;
    }
}
