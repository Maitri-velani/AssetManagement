package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.Asset;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.List;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class AssetDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    AssetDao assetDao;

    @Test
    public void create(){
        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("pqrst");
        asset.setType("dell");
        assetDao.create(asset);
        Asset asset1 = assetDao.getById("1");
        Assert.assertEquals(asset1.getName(),asset.getName());
        assetDao.delete("1");
    }

    @Test
    public void getAll(){
        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("pqrst");
        asset.setType("dell");
        assetDao.create(asset);

        Asset asset1 = new Asset();
        asset1.setId("2");
        asset1.setName("pqrst");
        asset1.setType("lenovo");
        assetDao.create(asset1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Asset> assets = assetDao.getAll();
        int size = assets.size();
        Assert.assertEquals(2,size);
        assetDao.delete("1");
        assetDao.delete("2");
    }

    @Test
    public void getById(){
        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("pqrst");
        asset.setType("dell");
        assetDao.create(asset);
        Asset asset1 = assetDao.getById("1");
        Assert.assertEquals(asset1.getType(),asset.getType());
        assetDao.delete("1");
    }

    @Test
    public void getByType(){
        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("pqrst");
        asset.setType("dell");
        assetDao.create(asset);

        Asset asset1 = new Asset();
        asset1.setId("2");
        asset1.setName("pqrst");
        asset1.setType("dell");
        assetDao.create(asset1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Asset> assets = assetDao.getByType("dell");
        int size = assets.size();
        Assert.assertEquals(2,size);
        assetDao.delete("1");
        assetDao.delete("2");
    }

    @Test
    public void delete(){
        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("pqrst");
        asset.setType("dell");
        assetDao.create(asset);

        assetDao.delete("1");
        Asset asset1=assetDao.getById("1");
        Assert.assertNull(asset1);
    }
}
