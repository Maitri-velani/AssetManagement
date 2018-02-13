package com.brevitaz.AssetManagement.controller;

import com.brevitaz.AssetManagement.model.Asset;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;


@RestController()
@RequestMapping("/owner")
public class OwnerController {

    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestBody Owner owner) throws IOException {
        return true;
    }


    @RequestMapping(method = {RequestMethod.GET})
    public List<Owner> getAll() throws IOException {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Owner owner,@PathVariable String ownerId) throws IOException {
        return true;
    }

    @RequestMapping(value = "/{ownerId}", method = {RequestMethod.DELETE})
    public boolean delete(@PathVariable String ownerId) throws IOException {
        return true;
    }


    @RequestMapping(value="/{ownerId}", method = {RequestMethod.GET})
    public List<Asset> getByOwner(@PathVariable String ownerId)
    {
        return new ArrayList<>();
    }

    @RequestMapping(value="/{ownerId}/request", method=RequestMethod.POST)
    public boolean request(@RequestBody Asset asset, @PathVariable String ownerId)
    {
        System.out.println("Request asset");
        return true;
    }
}
