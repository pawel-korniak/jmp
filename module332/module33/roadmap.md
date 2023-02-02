# Roadmap of Demonstration
[toc]
## Useful links
* [ElasticSearch documentation](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/searching.html)
* [SpringBoot](https://spring.io/projects/spring-boot)
## Points at issue:
- ***what is ElasticSearch?***
  Elasticsearch is a distributed search and analytics engine built on Apache Lucene. Since its release in 2010, Elasticsearch has quickly become the most popular search engine and is commonly used for log analytics, full-text search, security intelligence, business analytics, and operational intelligence use cases.
## Task Description

### Task 1 - Indexing (2 Points)
1. Find collection of the books in English language. It is better to use epub format.
2. Implement indexing process of these books.
3. Document should have the following fields: - Id - Title (book title, string) - Authors (collection of strings) - Content - Language
4. The process of indexing could be described as following: - Read all books from local folder - Use epubreader library (ex. com.positiondev.epublib) for transforming from File to EbubBook object - Then transform EpubBook to Book model which represent Solr document - Save Books to Solr
5. Implement API endpoint to start indexing process
6. Using spring data solr implement repository to retrieving solr document by id
7. Implement API endpoint
      GET /api/v1/book/{id}
      
### Task 2 - Search book by query (2 points)
- Implement API endpoint with following request:
      {
      "field": "string", // field for filtering
      "value": "string", // value of the field for filtering
      "facetField": "string", // field for facet
      "fulltext": true, // is full text search
      "q": "string" // query for full text search
      }
      The response structure is:
      {
      "books": [
      {
      "id": "string",
      "authors": [
      "string"
      ],
      "title": "string",
      "language": "string",
      "content": "string",
      }
      ],
      "facets": [
      {
      "valueCount": 0,
      "value": "string",
      "field": {
      "name": "string"
      },
      "key": {
      "name": "string"
      }
      }
      ],
      "numFound": 0
      }
- Using *org.springframework.data.solr.core.SolrTemplate * implement search request to Solr using parameters from response 
### Task 3 - Implement autosuggestions (1 Point)
- Implement API endpoint which is returning array of suggestions:
       GET api/v1/book/suggest?query=
- Create suggest field in scheme using org.springframework.data.solr.core.SolrTemplate which is filled from Book.title & Book.authors
- Add required searchComponent and requestHandler for suggestions to solrconfig.xml
- Using org.apache.solr.client.solrj.SolrClient execute suggest query

## Demo
### Implementation

As we agree I used Elastic Search instead of Solr,

more complicated query needs to be implemented(for example: grouping by, sorting)

### Presentation

docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
import events.postman_collection.json to postman
run evants/insert collection
try out events/QUERY requests

