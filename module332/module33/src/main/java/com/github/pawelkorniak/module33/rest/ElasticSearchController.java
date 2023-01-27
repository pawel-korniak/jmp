package com.github.pawelkorniak.module33.rest;

import com.github.pawelkorniak.module33.dto.Event;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("elastic")
public class ElasticSearchController {

    @Autowired
    RestHighLevelClient client;

    @GetMapping("getAll")
    public List<String> getAll() {
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse response;
        {
            try {
                response = client.search(searchRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        SearchHit[] searchHits = response.getHits().getHits();
        List<String> results =
                Arrays.stream(searchHits)
                        .map(SearchHit::getSourceAsString)
                        .collect(Collectors.toList());
        return results;
    }

    @GetMapping("get/{title}")
    public List<String> getByTitle(@PathVariable String title) {
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.matchQuery("title", title));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);
        SearchResponse response;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SearchHit[] searchHits = response.getHits().getHits();
        List<String> results =
                Arrays.stream(searchHits)
                        .map(SearchHit::getSourceAsString)
                        .collect(Collectors.toList());
        return results;
    }
}
