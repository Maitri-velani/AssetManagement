package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AssetDaoTest {

    @Autowired
    AssetDao assetDao;

    @Test
    public void create() throws IOException {
        Asset asset = new Asset();
        asset.setId("3");
        asset.setName("uwqe");
        asset.setType("lenovo");
        boolean status = assetDao.create(asset);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAll() throws IOException {
        List<Asset> assets = assetDao.getAll();
        int size = assets.size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void getById() throws IOException {
        Asset asset = assetDao.getById("2");
        Assert.assertNotNull(asset);
    }

    @Test
    public void getByType() throws IOException {
        List<Asset> assets = assetDao.getByType("lenovo");
        int size = assets.size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void delete() throws IOException {
        boolean status=assetDao.delete("1");
        Assert.assertEquals(true,status);
    }
}
