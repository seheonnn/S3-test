// package com.example.s3temp.V1;
//
// import java.util.List;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
//
// import lombok.RequiredArgsConstructor;
//
// @RequiredArgsConstructor
// @RestController
// @RequestMapping("/api/v1/uploadImage")
// public class S3ControllerV1 {
//     private final UploadService uploadService;
//     @PostMapping(value = "")
//     public ResponseEntity uploadImage(@RequestPart(value = "file", required = false) MultipartFile file) throws Exception{
//         String url = uploadService.uploadImage(file);
//         return new ResponseEntity<>(url, HttpStatus.OK);
//     }
//
//     @DeleteMapping(value = "")
//     public ResponseEntity deleteImage(@RequestPart(value = "path", required = false) String path) {
//         String image = path.substring(path.lastIndexOf('/')+1);
//         return new ResponseEntity<>(uploadService.deleteImage(image), HttpStatus.OK);
//     }
//
//     @PostMapping(value = "/images")
//     public ResponseEntity uploadImages(@RequestPart(value = "file", required = false) List<MultipartFile> file) throws Exception{
//         List<String> urls = uploadService.uploadImages(file);
//         return new ResponseEntity<>(urls, HttpStatus.OK);
//     }
//
// }
