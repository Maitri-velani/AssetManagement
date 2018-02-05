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
    public boolean createAsset(@RequestBody Asset asset)
    {
        System.out.println("Asset is created");
       // assets.add(asset);
        return true;
    }

    @RequestMapping(value = "/{assetId}",method = {RequestMethod.DELETE})
    public boolean deleteAsset(@PathVariable String assetId)
    {
        System.out.println("Asset is deleted");
        return true;
    }

    @RequestMapping(value = "/{assetId}/assign/{employeeId}", method = {RequestMethod.POST})
    public Boolean assignAsset(@RequestBody Asset asset)
    {
        System.out.println("Assign asset to employee Successfully");
        return true;
    }

    @RequestMapping(method = {RequestMethod.GET})
    public void get()
    {
        System.out.println("Get assets");
    }

    @RequestMapping(value = "/{assetId}/unassign/{employeeId}",method = {RequestMethod.DELETE})
    public boolean unassign(@PathVariable String assetId, String employeeId)
    {
        System.out.println("Deallocate asset from employee");
        return true;
    }
}
