package com.brevitaz.AssetManagement.dao.impl;

import com.brevitaz.AssetManagement.config.ESConfig;
import com.brevitaz.AssetManagement.dao.AssetDao;
import com.brevitaz.AssetManagement.model.Asset;
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
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean create(Asset asset) throws IOException {

        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                asset.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(asset);
        request.source(json, XContentType.JSON);
        IndexResponse response = esConfig.getEsClient().index(request);
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
    public boolean delete(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        DeleteResponse response = esConfig.getEsClient().delete(request);
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
    public List<Asset> getAll() throws IOException {
        List<Asset> assets = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse response = esConfig.getEsClient().search(request);
        SearchHit[] hits = response.getHits().getHits();
        Asset asset;
        for (SearchHit hit : hits) {
            asset = objectMapper.readValue(hit.getSourceAsString(), Asset.class);
            assets.add(asset);
        }
        return assets;
    }

    @Override
    public Asset getById(String id) throws IOException {
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        GetResponse response = esConfig.getEsClient().get(request);
        Asset asset = objectMapper.readValue(response.getSourceAsString(), Asset.class);
        if (response.isExists()) {
            return asset;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Asset> getByType(String type) throws IOException {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("type", type)));
        request.source(sourceBuilder);
        List<Asset> assets=new ArrayList<>();
        SearchResponse response = esConfig.getEsClient().search(request);
        SearchHit[] hits = response.getHits().getHits();

        Asset asset;
        for (SearchHit hit : hits)
        {
            asset = objectMapper.readValue(hit.getSourceAsString(), Asset.class);
            assets.add(asset);
        }
        return assets;
    }
}
