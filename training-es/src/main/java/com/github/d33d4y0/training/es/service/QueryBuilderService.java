package com.github.d33d4y0.training.es.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.geometry.Point;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.d33d4y0.training.es.config.ElasticsearchConfig;
import com.github.d33d4y0.training.es.entity.StudentEntity;

@Service
public class QueryBuilderService {

	@Autowired
	private RestHighLevelClient esClient;

	@Autowired
	private ElasticsearchConfig esConfig;

	private static final JsonMapper JSON_MAPPER = JsonMapper.builder()
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
			.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
			.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).serializationInclusion(Include.ALWAYS)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();

	static {
		JSON_MAPPER.findAndRegisterModules();
	}

	public StudentEntity findByCitizenId() throws IOException {
		StudentEntity entity = null;
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(1);
		searchSourceBuilder.query(QueryBuilders.termQuery("citizenId.keyword", "1111111111111"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entity = convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class);
		}
		return entity;
	}

	public List<StudentEntity> findByAge() throws IOException {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.termQuery("age", 24));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByCitizenIdIsNot() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder mainQuery = QueryBuilders.boolQuery();
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		mainQuery.filter(boolQuery.mustNot(QueryBuilders.termQuery("citizenId.keyword", "1111111111111")));
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(mainQuery);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByIsGraduatedFalse() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.termQuery("isGraduated", false));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByNameStartingWith() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("name.keyword", "D33d4y*"));
//		searchSourceBuilder.query(QueryBuilders.regexpQuery("name.keyword", "@&(D33d4y.+)"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByAgeLessThan() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.rangeQuery("age").lt(30));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByAgeBetween() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.rangeQuery("age").from(20).to(30));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByRegisteredDateTimeAfter() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.rangeQuery("registeredDateTime")
				.gt(LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS"))));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByAgeOrderByRegisteredDateTimeDesc() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder mainQuery = QueryBuilders.boolQuery();
		mainQuery.filter(QueryBuilders.matchQuery("age", 24));
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(mainQuery);
		searchSourceBuilder.sort(SortBuilders.fieldSort("registeredDateTime").order(SortOrder.DESC));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public List<StudentEntity> findByAddressDistrict() {
		List<StudentEntity> entities = new LinkedList<>();
		SearchRequest searchRequest = new SearchRequest("student-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(10);
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.nestedQuery("address",
				QueryBuilders.termQuery("address.district.keyword", "Pak Khlong Phasi Charoen"), ScoreMode.Avg)));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}
		return entities;
	}

	public <T> T findByLatitudeAndLongitude(float lat, float lon, Class<T> clazz) throws IOException {
		Point point = new Point(lon, lat);
		SearchRequest searchRequest = new SearchRequest("sub-district-boundary-index");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(1);
		searchSourceBuilder.query(QueryBuilders.geoIntersectionQuery("geometry", point));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		Object info;
		if (searchHits.length > 0) {
			info = convertJsonStrToObj(searchHits[0].getSourceAsString(), clazz);
		} else {
			info = new Object();
		}
		return clazz.cast(info);
	}

	public List<StudentEntity> scrolling() {
		List<StudentEntity> entities = new LinkedList<>();
		Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
		SearchRequest searchRequest = new SearchRequest("student-index");
		searchRequest.scroll(scroll);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.size(esConfig.getScrollSize());
		searchSourceBuilder.query(QueryBuilders.termQuery("age", 24));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse;
		String scrollId;
		try {
			searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
			scrollId = searchResponse.getScrollId();
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform searching");
		}
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
		}

		while (searchHits != null && searchHits.length > 0) {
			SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
			scrollRequest.scroll(scroll);
			try {
				searchResponse = esClient.scroll(scrollRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				throw new RuntimeException("Cannot peroform searching");
			}
			scrollId = searchResponse.getScrollId();
			searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				entities.add(convertJsonStrToObj(hit.getSourceAsString(), StudentEntity.class));
			}
		}
		ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
		clearScrollRequest.addScrollId(scrollId);
		try {
			esClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entities;
	}

	private <T> T convertJsonStrToObj(String jsonStr, Class<T> clazz) {
		try {
			return JSON_MAPPER.readValue(jsonStr, clazz);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Cannot convert json", e);
		}
	}
}
