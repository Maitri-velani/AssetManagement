package com.brevitaz.AssetManagement.dao.impl;

import com.brevitaz.AssetManagement.config.ESConfig;
import com.brevitaz.AssetManagement.dao.AssetOwnerDao;
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

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository
public class AssetOwnerDaoImpl implements AssetOwnerDao {

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="owner";

    @Autowired
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean create(AssetOwner assetOwner) throws IOException {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                assetOwner.getOwnerId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(assetOwner);
        request.source(json, XContentType.JSON);
        IndexResponse response = esConfig.getEsClient().index(request);
        if (response.status()== RestStatus.CREATED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<AssetOwner> getAll() throws IOException {
        List<AssetOwner> assetOwners = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse response = esConfig.getEsClient().search(request);
        SearchHit[] hits = response.getHits().getHits();
        AssetOwner assetOwner;
        for (SearchHit hit : hits) {
            assetOwner = objectMapper.readValue(hit.getSourceAsString(), AssetOwner.class);
            assetOwners.add(assetOwner);
        }
        return assetOwners;
    }

    @Override
    public boolean update(AssetOwner assetOwner, String ownerId) throws IOException {
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                ownerId);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(assetOwner);
        request.doc(json, XContentType.JSON);
        UpdateResponse response = esConfig.getEsClient().update(request);
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
    public boolean delete(String ownerId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                ownerId
        );
        DeleteResponse response = esConfig.getEsClient().delete(request);
        if (response.status()==RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<AssetOwner> getOwnerByName(String ownerName) throws IOException {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(termQuery("assetOwnerName", ownerName)));
        request.source(sourceBuilder);

        List<AssetOwner> assetOwners=new ArrayList<>();

        SearchResponse response = esConfig.getEsClient().search(request);

        SearchHit[] hits = response.getHits().getHits();
        AssetOwner assetOwner;
        for (SearchHit hit : hits)
        {
            assetOwner = objectMapper.readValue(hit.getSourceAsString(), AssetOwner.class);
            assetOwners.add(assetOwner);
        }

        return assetOwners;
    }

    @Override
    public AssetOwner getOwnerById(String ownerId) throws IOException {
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                ownerId);

        GetResponse response = esConfig.getEsClient().get(request);
        AssetOwner assetOwner = objectMapper.readValue(response.getSourceAsString(), AssetOwner.class);

        if (response.isExists()) {
            return assetOwner;
        }
        else
        {
            return null;
        }
    }
}
