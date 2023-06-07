package com.ceres.blog.images.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IService {
    String uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(String name) throws  IOException;
}
