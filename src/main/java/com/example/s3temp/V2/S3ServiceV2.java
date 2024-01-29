package com.example.s3temp.V2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3ServiceV2 {

	private final AmazonS3 amazonS3;

	private final S3ConfigV2 s3ConfigV2;

	public String uploadFile(MultipartFile file) {
		String filePath =
			UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		try {
			amazonS3.putObject(
				new PutObjectRequest(s3ConfigV2.getBucket(), s3ConfigV2.getFolder() + filePath, file.getInputStream(),
					metadata));
		} catch (IOException e) {
			log.error("error at AmazonS3Manager uploadFile : {}", (Object)e.getStackTrace());
		}

		return amazonS3.getUrl(s3ConfigV2.getBucket(), filePath).toString();
	}

	public String deleteImage(String image) {
		amazonS3.deleteObject(s3ConfigV2.getBucket(), s3ConfigV2.getFolder() + image);
		return "삭제 성공";
	}

	public List<String> uploadFiles(List<MultipartFile> files) {
		return files.stream()
			.map(this::uploadFile)
			.toList();
	}

}
