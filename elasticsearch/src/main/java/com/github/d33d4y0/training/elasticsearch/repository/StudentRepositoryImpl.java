package com.github.d33d4y0.training.elasticsearch.repository;

import java.io.IOException;
import java.util.Collections;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepositoryImpl implements StudentRepositoryCustom{

	@Autowired
	private RestHighLevelClient esClient;
	
	@Override
	public void updateGraduated(String citizenId, boolean graduated) {
		UpdateByQueryRequest updateRequestQuery = new UpdateByQueryRequest("student-index");
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.termQuery("citizenId.keyword", citizenId));
		updateRequestQuery.setQuery(boolQuery);
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("ctx._source.graduated = \'");
		strBuilder.append(String.valueOf(graduated));
		strBuilder.append("\'");
		Script script = new Script(ScriptType.INLINE, "painless", strBuilder.toString(), Collections.emptyMap());
		updateRequestQuery.setScript(script);
		updateRequestQuery.setTimeout(TimeValue.timeValueMinutes(10));
		updateRequestQuery.setSlices(0);
		try {
			esClient.updateByQuery(updateRequestQuery, RequestOptions.DEFAULT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot peroform update");
		}
		
	}

}
