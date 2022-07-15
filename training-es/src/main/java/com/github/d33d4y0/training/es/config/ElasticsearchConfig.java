package com.github.d33d4y0.training.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
@PropertySource("file:${elasticsearch.config.path}")
public class ElasticsearchConfig {

	@Value("${elasticsearch.host}")
	private String host;

	@Value("${elasticsearch.port}")
	private int port;

	@Value("${elasticsearch.scroll.size}")
	private int scrollSize;

	@Value("${elasticsearch.save.size}")
	private int saveSize;

	@Value("${elasticsearch.username}")
	private String userName;

	@Value("${elasticsearch.password}")
	private String password;

	@Bean
	public RestHighLevelClient restClient() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

		RestClientBuilder builder = RestClient.builder(new HttpHost(host, port)).setHttpClientConfigCallback(
				httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

		RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;

	}

	@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(restClient());
    }
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getScrollSize() {
		return scrollSize;
	}

	public int getSaveSize() {
		return saveSize;
	}
}
