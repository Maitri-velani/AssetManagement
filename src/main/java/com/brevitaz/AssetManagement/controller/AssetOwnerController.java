package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.AssetOwnerDao;
import com.brevitaz.AssetManagement.model.AssetOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController()
@RequestMapping("api/owner")
public class AssetOwnerController {

    @Autowired
    AssetOwnerDao assetOwnerDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody AssetOwner assetOwner){
        return assetOwnerDao.create(assetOwner);
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<AssetOwner> getAll(){
        return assetOwnerDao.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody AssetOwner assetOwner, @PathVariable String id){
        return assetOwnerDao.update(assetOwner, id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id){
        return assetOwnerDao.delete(id);
    }


    @RequestMapping(value="/name/{firstName}", method = {RequestMethod.GET})
    public List<AssetOwner> getOwnerByName(@PathVariable String firstName){
        return assetOwnerDao.getOwnerByName(firstName);
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.GET})
    public AssetOwner getByOwnerId(@PathVariable String id){
        return assetOwnerDao.getOwnerById(id);
    }
}
