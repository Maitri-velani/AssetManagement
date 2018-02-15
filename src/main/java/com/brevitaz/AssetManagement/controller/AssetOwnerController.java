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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody AssetOwner assetOwner, @PathVariable String id) throws IOException {
        return assetOwnerDao.update(assetOwner, id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) throws IOException {
        return assetOwnerDao.delete(id);
    }


    @RequestMapping(value="/name/{firstName}", method = {RequestMethod.GET})
    public List<AssetOwner> getOwnerByName(@PathVariable String firstName) throws IOException {
        return assetOwnerDao.getOwnerByName(firstName);
    }

    @RequestMapping(value="/{id}", method = {RequestMethod.GET})
    public AssetOwner getByOwnerId(@PathVariable String id) throws IOException {
        return assetOwnerDao.getOwnerById(id);
    }
}
