// package com.example.s3temp.V1;
//
// import java.io.IOException;
// import java.net.URLEncoder;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.amazonaws.services.s3.AmazonS3Client;
// import com.amazonaws.services.s3.model.ObjectMetadata;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class S3ServiceV1 {
//     @Value("${cloud.aws.s3.bucket}")
//     private String bucket;
//
//     @Value("${cloud.aws.cloudfront}")
//     private String url;
//
//     @Value("${cloud.aws.s3.folder}")
//     private String folder;
//
//     private final AmazonS3Client amazonS3Client;
//
//     public String uploadImage(MultipartFile file) throws IOException {
//
//
//         // if (file.getSize() > 1000000) {
//         //     throw new BaseException(FILE_SIZE_LIMIT);
//         // }
//         String filePath = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//         ObjectMetadata metadata = new ObjectMetadata();
//         metadata.setContentType(file.getContentType());
//         metadata.setContentLength(file.getSize());
//         metadata.addUserMetadata("originfilename", URLEncoder.encode(folder + filePath, StandardCharsets.UTF_8));
//         amazonS3Client.putObject(bucket, folder + filePath, file.getInputStream(), metadata);
//         return url + folder + filePath;
//     }
//
//     public String deleteImage(String image) {
//         amazonS3Client.deleteObject(bucket, folder + image);
//         return "삭제 성공";
//     }
//
//
//     // 여러 개의 이미지를 업로드하는 경우
//     public List<String> uploadImages(List<MultipartFile> files) {
//         // stream
//         List<String> imagesUrls = new ArrayList<>();
//         files.stream().map(file -> {
//             String filePath = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//             ObjectMetadata metadata = new ObjectMetadata();
//             metadata.setContentType(file.getContentType());
//             metadata.setContentLength(file.getSize());
//             metadata.addUserMetadata("originfilename", URLEncoder.encode(folder + filePath, StandardCharsets.UTF_8));
//             imagesUrls.add(url + folder + filePath);
//             try {
//                 amazonS3Client.putObject(bucket, folder + filePath, file.getInputStream(), metadata);
//             } catch (IOException e) {
//                 throw new RuntimeException(e);
//             }
//             return "Uploaded successfully!";
//         }).collect(Collectors.toList());
//         return imagesUrls;
//     }
// }
