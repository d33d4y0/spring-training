package com.github.d33d4y0.training.uploadfile.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	@Autowired
	@Qualifier("simpleRestTemplate")
	private RestTemplate restTemplate;
	private static final String IP;
	@Value("${server.port}")
	private int port;
	@Value("${server.servlet.context-path}")
	private String BASE_PATH;
	private static final String UPLOAD_PATH = "/upload";
	private static final String UPLOADS_PATH = "/uploads";

	static {
		try {
			IP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException("Cannot get current IP address", e);
		}
	}

	public void uploadFile(MultipartFile file) throws IOException {
		FileUtils.writeByteArrayToFile(new File("src/main/resources/" + file.getOriginalFilename()), file.getBytes());
	}

	public void uploadFiles(MultipartFile[] files) throws IOException {
		for (MultipartFile file : files) {
			uploadFile(file);
		}
	}

//	Example for RestTemplate calling with multipart
	public void uploadFileWithRestTemplate(MultipartFile file) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		body.add("file", file.getResource());
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
		String uri = "http://" + IP + ":" + port + BASE_PATH + UPLOAD_PATH;
		restTemplate.exchange(uri, HttpMethod.POST, entity, Void.class);
	}

	public void uploadFileWithRestTemplate(MultipartFile[] files) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		for (MultipartFile file : files) {
			body.add("files", file.getResource());
		}
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
		String uri = "http://" + IP + ":" + port + BASE_PATH + UPLOADS_PATH;
		restTemplate.exchange(uri, HttpMethod.POST, entity, Void.class);
	}
}