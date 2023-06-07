package com.ceres.blog.images.service;

import com.ceres.blog.images.model.PostImage;
import com.ceres.blog.images.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IService {
    private final ImageRepository repository;
    //Set folder where you want to save the file
    private final String STORAGE_LOCATION = "/home/artemis/Desktop/BlogImages/";
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        //Define how image should be saved in the defined path
        String filePath = STORAGE_LOCATION+file.getOriginalFilename();
        PostImage postImage = repository.save(
                PostImage.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .build()
        );
        //Save the image to the specified path
        file.transferTo(new File(filePath));
        return "[+] File Successfully uploaded";
    }

    @Override
    public byte[] downloadImage(String name) throws IOException {
        Optional<PostImage> postImage = repository.findByName(name);
        String filePath = postImage.get().getFilePath();
        return Files.readAllBytes(new  File(filePath).toPath());
    }
}
