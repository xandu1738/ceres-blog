package com.ceres.blog.images.controller;

import com.ceres.blog.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService service;
    @PostMapping("/upload")
    ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) throws IOException {
        String image = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
    @GetMapping("/{name}")
    ResponseEntity<?> downloadImage(@PathVariable String name) throws IOException {
        byte[] imageData = service.downloadImage(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
