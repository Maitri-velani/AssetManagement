package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.dao.AssetOwnerDao;
import com.brevitaz.AssetManagement.model.AssetOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController()
@RequestMapping("/owner")
public class AssetOwnerController {

    @Autowired
    AssetOwnerDao assetOwnerDao;

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody AssetOwner assetOwner) throws IOException {
        return assetOwnerDao.create(assetOwner);
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<AssetOwner> getAll() throws IOException {
        return assetOwnerDao.getAll();
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody AssetOwner assetOwner, @PathVariable String ownerId) throws IOException {
        return assetOwnerDao.update(assetOwner, ownerId);
    }

    @RequestMapping(value = "/{ownerId}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String ownerId) throws IOException {
        return assetOwnerDao.delete(ownerId);
    }


    @RequestMapping(value="/{firstName}", method = {RequestMethod.GET})
    public List<AssetOwner> getOwnerByName(@PathVariable String firstName) throws IOException {
        return assetOwnerDao.getOwnerByName(firstName);
    }

    @RequestMapping(value="/id/{ownerId}", method = {RequestMethod.GET})
    public AssetOwner getByOwnerId(@PathVariable String ownerId) throws IOException {
        return assetOwnerDao.getOwnerById(ownerId);
    }
}
