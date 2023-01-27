package com.github.pawelkorniak.module33.rest.config;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient getClient(){
        ClientConfiguration clientConfiguration =
        ClientConfiguration.builder().connectedTo("localhost:9200").build();
        //works with docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return client;
    }

    @Bean
    public IndexRequest request(){
        return new IndexRequest("events");
    }
}
