package com.brevitaz.AssetManagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private RestHighLevelClient client;
    private ObjectMapper objectMapper;

    public Config() {
    }
    public void ESConfig(RestHighLevelClient esClient)
    {
        this.client=client;
    }

    @Bean
    public RestHighLevelClient getEsClient() {
        if (client == null) {
            this.client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")).build());
        }
        return client;
    }

    @Bean
    public ObjectMapper objectMapper()
    {
        if(objectMapper == null)
        {
            objectMapper=new ObjectMapper();
        }
        return objectMapper;
    }

}
