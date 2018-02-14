package com.brevitaz.AssetManagement.dao.impl;



import com.brevitaz.AssetManagement.config.ESConfig;
import com.brevitaz.AssetManagement.dao.RequestDao;
import com.brevitaz.AssetManagement.model.Request;
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
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestDaoImpl implements RequestDao {

    public static final String INDEX_NAME = "request";
    public static final String TYPE_NAME = "doc";

    @Autowired
    private ESConfig esConfig;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean insert(Request request, String ownerId) throws IOException {
        IndexRequest indexRequest = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                request.getRequestId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(request);
        indexRequest.source(json, XContentType.JSON);
        IndexResponse response = esConfig.getEsClient().index(indexRequest);
        if (response.status() == RestStatus.OK)
            return true;
        else
            return false;
    }

    @Override
    public List<Request> getAll() throws IOException {
        List<Request> requests = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        searchRequest.types(TYPE_NAME);
        SearchResponse response = esConfig.getEsClient().search(searchRequest);
        SearchHit[] hits = response.getHits().getHits();
        Request request;
        for (SearchHit hit : hits)
        {
            request=objectMapper.readValue(hit.getSourceAsString(),Request.class);
            requests.add(request);
        }
        return requests;
    }

    @Override
    public boolean delete(String requestId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                requestId
        );
        DeleteResponse response = esConfig.getEsClient().delete(request);
        if (response.status()==RestStatus.NOT_FOUND)
            return true;
        else
            return false;
    }

    @Override
    public Request getById(String requestId) throws IOException {
        GetRequest getRequest = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                requestId
        );
        GetResponse response = esConfig.getEsClient().get(getRequest);
        Request request = objectMapper.readValue(response.getSourceAsString(),Request.class);
        if(response.isExists())
            return request;
        else
            return null;
    }
}