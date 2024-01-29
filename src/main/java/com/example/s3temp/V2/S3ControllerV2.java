package com.example.s3temp.V2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/s3")
public class S3ControllerV2 {

	private final S3ServiceV2 s3Service;

	@PostMapping(value = "/uploadImage", consumes = "multipart/form-data")
	public ResponseEntity uploadImage(@RequestPart(value = "file", required = false) MultipartFile file){
		String url = s3Service.uploadFile(file);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteImage", consumes = "multipart/form-data")
	public ResponseEntity deleteImage(@RequestPart(value = "path", required = false) String path) {
		String image = path.substring(path.lastIndexOf('/') + 1);
		return new ResponseEntity<>(s3Service.deleteImage(image), HttpStatus.OK);
	}

	@PostMapping(value = "/uploadImages", consumes = "multipart/form-data")
	public ResponseEntity uploadImages(@RequestPart(value = "files", required = false) List<MultipartFile> files) {
		List<String> urls = s3Service.uploadFiles(files);
		return new ResponseEntity<>(urls, HttpStatus.OK);
	}

}
