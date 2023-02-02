package com.github.pawelkorniak.module33.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElasticQuery {
    public String field;
    public String value;
    public String facet;
    public boolean fulltext;
    public String q;
}
