package com.github.d33d4y0.training.uploadfile.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.d33d4y0.training.uploadfile.service.UploadService;

@RestController
public class UploadController {

	@Autowired
	private UploadService service;

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> uploadFile(@RequestPart MultipartFile file) throws IOException {
		service.uploadFile(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/uploads", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> uploadFiles(@RequestPart MultipartFile[] files) throws IOException {
		service.uploadFiles(files);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/upload-resttemplate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> uploadFileWithRestTemplate(@RequestPart MultipartFile file) throws IOException {
		service.uploadFileWithRestTemplate(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/uploads-resttemplate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> uploadFileWithRestTemplate(@RequestPart MultipartFile[] files) throws IOException {
		service.uploadFileWithRestTemplate(files);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}