package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.AssetOwner;
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
public class AssetOwnerDaoTest {

    @Autowired
    AssetOwnerDao assetOwnerDao;

    @Test
    public void create() throws IOException {
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("3");
        assetOwner.setFirstName("abc");
        boolean status = assetOwnerDao.create(assetOwner);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAll() throws IOException {
        List<AssetOwner> assetOwners = assetOwnerDao.getAll();
        int size=assetOwners.size();
        Assert.assertEquals(1,size);
    }

    @Test
    public void update() throws IOException {
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setFirstName("pqrs");
        assetOwner.setLastName("rygfhf");
        boolean status=assetOwnerDao.update(assetOwner,"1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void delete() throws IOException {
        boolean status=assetOwnerDao.delete("1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void getOwnerByName() throws IOException {
        List<AssetOwner> assetOwners = assetOwnerDao.getOwnerByName("abc");
        int size = assetOwners.size();
        Assert.assertEquals(2,size);
    }

    @Test
    public void getById() throws IOException {
        AssetOwner assetOwner = assetOwnerDao.getOwnerById("2");
        Assert.assertNotNull(assetOwner);
    }
}
