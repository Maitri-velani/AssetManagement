package com.brevitaz.AssetManagement.dao.impl;

import com.brevitaz.AssetManagement.dao.AssetDao;
import com.brevitaz.AssetManagement.model.Asset;
import com.brevitaz.AssetManagement.model.AssetOwner;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Repository
public class AssetDaoImpl implements AssetDao {

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="asset";

    @Autowired
    private RestHighLevelClient getEsClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean create(Asset asset){

        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                asset.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = objectMapper.writeValueAsString(asset);
            request.source(json, XContentType.JSON);
            IndexResponse response = getEsClient.index(request);
            if(response.status()== RestStatus.OK) {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean delete(String id){
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        try {
            DeleteResponse response = getEsClient.delete(request);
            if (response.status()==RestStatus.NOT_FOUND)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Asset> getAll(){
        List<Asset> assets = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse response = null;
        try {
            response = getEsClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] hits = response.getHits().getHits();
        Asset asset=null;
        for (SearchHit hit : hits) {
            try {
                asset = objectMapper.readValue(hit.getSourceAsString(), Asset.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assets.add(asset);
        }
        return assets;
    }

    @Override
    public Asset getById(String id){
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        try {

            GetResponse response = getEsClient.get(request);
            Asset asset = objectMapper.readValue(response.getSourceAsString(), Asset.class);
            if (response.isExists()) {
                return asset;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Asset> getByType(String type){
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("type", type)));
        request.source(sourceBuilder);
        List<Asset> assets=new ArrayList<>();
        SearchResponse response = null;
        try {
            response =getEsClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] hits = response.getHits().getHits();

        Asset asset = null;
        for (SearchHit hit : hits)
        {
            try {
                asset = objectMapper.readValue(hit.getSourceAsString(), Asset.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assets.add(asset);
        }
        return assets;
    }

    @Override
    public List<AssetOwner> getAllOwner() {
        List<AssetOwner> assetOwners = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse response = null;
        try {
            response =getEsClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] hits = response.getHits().getHits();
        AssetOwner assetOwner = null;
        for (SearchHit hit : hits) {
            try {
                assetOwner = objectMapper.readValue(hit.getSourceAsString(), AssetOwner.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assetOwners.add(assetOwner);
        }
        return assetOwners;
    }

    public List<AssetOwner> getOwnerByName(String firstName){
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("firstName", firstName)));
        request.source(sourceBuilder);

        List<AssetOwner> assetOwners=new ArrayList<>();

        SearchResponse response = null;
        try {
            response = getEsClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchHit[] hits = response.getHits().getHits();
        AssetOwner assetOwner = null;
        for (SearchHit hit : hits)
        {
            try {
                assetOwner = objectMapper.readValue(hit.getSourceAsString(), AssetOwner.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assetOwners.add(assetOwner);
        }
        return assetOwners;
    }

    public AssetOwner getOwnerById(String id){
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        try {

            GetResponse response = getEsClient.get(request);
            AssetOwner assetOwner = objectMapper.readValue(response.getSourceAsString(), AssetOwner.class);
            if (response.isExists()) {
                return assetOwner;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
