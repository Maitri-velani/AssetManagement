package com.brevitaz.AssetManagement.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    private RestHighLevelClient esClient;

    public ESConfig() {
    }
    public void ESConfig(RestHighLevelClient esClient)
    {
        this.esClient=esClient;
    }

    public RestHighLevelClient getEsClient() {
        if (esClient == null) {
            this.esClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));
        }
        return esClient;
    }
}
