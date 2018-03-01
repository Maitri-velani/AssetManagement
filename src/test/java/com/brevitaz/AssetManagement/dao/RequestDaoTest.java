package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;
import com.brevitaz.AssetManagement.model.Request;
import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class RequestDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    RequestDao requestDao;

    @Test
    public void create(){
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
        requestDao.create(request,"1");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Request request1 = requestDao.getById("1");
        Assert.assertEquals(request1.getAssets(),request.getAssets());
        requestDao.delete("1");
    }

    @Test
    public void getAll(){
        List<Asset> assets = new ArrayList<>();
        Asset asset = new Asset();
        asset.setName("lenovo");
        assets.add(asset);

        Request request = new Request();
        request.setId("1");
        request.setAssets(assets);
        requestDao.create(request,"1");

        List<Request> requests = requestDao.getAll();
        int size = requests.size();
        Assert.assertEquals(1,size);
        requestDao.delete("1");
    }

    @Test
    public void delete(){
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

        requestDao.create(request,"1");
        requestDao.delete("1");
        Request request1 = requestDao.getById("1");
        Assert.assertNull(request1);
    }

    @Test
    public void getById(){
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
        requestDao.create(request,"1");

        Request request1 = requestDao.getById("1");
        Assert.assertEquals(request1.getAssets(),request.getAssets());
        requestDao.delete("1");
    }
}
