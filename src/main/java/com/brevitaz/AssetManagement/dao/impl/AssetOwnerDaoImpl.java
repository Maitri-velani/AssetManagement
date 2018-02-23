package com.brevitaz.AssetManagement.dao.impl;

import com.brevitaz.AssetManagement.config.ESConfig;
import com.brevitaz.AssetManagement.dao.AssetOwnerDao;
import com.brevitaz.AssetManagement.model.AssetOwner;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
public class AssetOwnerDaoImpl implements AssetOwnerDao {

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="owner";

    @Autowired
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean create(AssetOwner assetOwner){
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                assetOwner.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json= null;
        try {
            json = objectMapper.writeValueAsString(assetOwner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.source(json, XContentType.JSON);
        IndexResponse response = null;
        try {
            response = esConfig.getEsClient().index(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.status()== RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<AssetOwner> getAll() {
        List<AssetOwner> assetOwners = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse response = null;
        try {
            response = esConfig.getEsClient().search(request);
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

    @Override
    public boolean update(AssetOwner assetOwner, String id){
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(assetOwner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request.doc(json, XContentType.JSON);
        UpdateResponse response = null;
        try {
            response = esConfig.getEsClient().update(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.status() == RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean delete(String id){
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        DeleteResponse response = null;
        try {
            response = esConfig.getEsClient().delete(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.status()==RestStatus.NOT_FOUND)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<AssetOwner> getOwnerByName(String firstName){
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("firstName", firstName)));
        request.source(sourceBuilder);

        List<AssetOwner> assetOwners=new ArrayList<>();

        SearchResponse response = null;
        try {
            response = esConfig.getEsClient().search(request);
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

    @Override
    public AssetOwner getOwnerById(String id){
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        GetResponse response = null;
        try {
            response = esConfig.getEsClient().get(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AssetOwner assetOwner = null;
        try {
            assetOwner = objectMapper.readValue(response.getSourceAsString(), AssetOwner.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isExists()) {
            return assetOwner;
        }
        else
        {
            return null;
        }
    }
}
