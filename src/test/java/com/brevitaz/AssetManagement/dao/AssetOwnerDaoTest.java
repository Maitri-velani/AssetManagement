package com.brevitaz.AssetManagement.dao;

import com.brevitaz.AssetManagement.model.AssetOwner;
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
import java.util.List;

@RunWith(RandomizedRunner.class)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@SpringBootTest
public class AssetOwnerDaoTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    AssetOwnerDao assetOwnerDao;

    @Test
    public void create(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("3");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);
        AssetOwner assetOwner1 = assetOwnerDao.getOwnerById("3");
        Assert.assertEquals(assetOwner1.getFirstName(),assetOwner.getFirstName());
        assetOwnerDao.delete("3");
    }

    @Test
    public void getAll(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("1");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);

        AssetOwner assetOwner1 = new AssetOwner();
        assetOwner1.setId("2");
        assetOwner1.setFirstName("abc");
        assetOwnerDao.create(assetOwner1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<AssetOwner> assetOwners = assetOwnerDao.getAll();
        int size=assetOwners.size();
        Assert.assertEquals(2,size);
        assetOwnerDao.delete("1");
        assetOwnerDao.delete("2");
    }

    @Test
    public void update(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("1");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);

        AssetOwner assetOwner1 = new AssetOwner();
        assetOwner1.setFirstName("pqrs");
        assetOwner1.setLastName("mno");
        assetOwnerDao.update(assetOwner1,"1");
        AssetOwner assetOwner2 =assetOwnerDao.getOwnerById("1");
        Assert.assertEquals(assetOwner2.getFirstName(),assetOwner1.getFirstName());
        assetOwnerDao.delete("1");
    }

    @Test
    public void delete(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("1");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);
        assetOwnerDao.delete("1");
        AssetOwner assetOwner1=assetOwnerDao.getOwnerById("1");
        Assert.assertNull(assetOwner1);
    }

    @Test
    public void getOwnerByName(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("1");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);

        AssetOwner assetOwner1 = new AssetOwner();
        assetOwner1.setId("2");
        assetOwner1.setFirstName("abc");
        assetOwnerDao.create(assetOwner1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<AssetOwner> assetOwners = assetOwnerDao.getOwnerByName("abc");
        int size = assetOwners.size();
        Assert.assertEquals(2,size);
        assetOwnerDao.delete("1");
        assetOwnerDao.delete("2");
    }

    @Test
    public void getById(){
        AssetOwner assetOwner = new AssetOwner();
        assetOwner.setId("1");
        assetOwner.setFirstName("abc");
        assetOwnerDao.create(assetOwner);
        AssetOwner assetOwner1 = assetOwnerDao.getOwnerById("1");
        Assert.assertEquals(assetOwner1.getFirstName(),assetOwner.getFirstName());
    }
}
