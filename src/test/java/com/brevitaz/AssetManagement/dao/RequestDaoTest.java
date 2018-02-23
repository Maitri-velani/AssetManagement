package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;
import com.brevitaz.AssetManagement.model.Request;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RequestDaoTest {

    @Autowired
    RequestDao requestDao;

    @Test
    public void create() throws IOException {
        List<Asset> assets = new ArrayList<>();
        Asset asset=new Asset();
        asset.setName("mouse");
        assets.add(asset);

        Asset asset1 = new Asset();
        asset1.setName("lenovo");
        assets.add(asset1);

        Request request = new Request();
        request.setId("1");
        request.setAssets(assets);

        boolean status = requestDao.create(request,"1");
        System.out.println(request);
        Assert.assertEquals(true,status);
    }

    @Test
    public void getAll() throws IOException {
        List<Request> requests = requestDao.getAll();
        int size = requests.size();
        Assert.assertEquals(1,size);
    }

    @Test
    public void delete() throws IOException {
        boolean status = requestDao.delete("1");
        Assert.assertEquals(true,status);
    }

    @Test
    public void getById() throws IOException {
        Request request = requestDao.getById("1");
        Assert.assertNotNull(request);
    }
}
