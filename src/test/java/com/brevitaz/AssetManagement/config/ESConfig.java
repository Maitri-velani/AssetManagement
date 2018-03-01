package com.brevitaz.AssetManagement.config;
import io.netty.util.NetUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.port}")
    Integer portNumber;

    @Value("${elasticsearch.host}")
    String host;

    @Value("${elasticsearch.scheme}")
    String scheme;

    @Value("$(elasticsearch.clusterName")
    String clusterName;

    private RestHighLevelClient esClient;
    private EmbeddedElastic embeddedElastic;

    @Bean
    public RestHighLevelClient getEsClient() {
        if (esClient == null) {
            this.esClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, portNumber, scheme)).build());
        }
        return esClient;
    }

    public void setEsClient(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }
    public ESConfig() {

    }

    @Bean
    public RestHighLevelClient client() throws Exception {
        Settings settings = Settings.builder()
                .put(PopularProperties.CLUSTER_NAME, clusterName)
                .build();

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, portNumber, scheme)).build());

        return restHighLevelClient;
    }

    @PostConstruct
    public void startES() throws Exception {
        try {
            embeddedElastic = EmbeddedElastic.builder()
                    .withElasticVersion("5.4.1").withEsJavaOpts("-Xms1g -Xmx1g")
                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                    .withSetting(PopularProperties.HTTP_PORT, 9200)
                    .withSetting(PopularProperties.CLUSTER_NAME, clusterName).withStartTimeout(60, TimeUnit.SECONDS)
                    .build()
                    .start();
            System.out.println("Es started");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}