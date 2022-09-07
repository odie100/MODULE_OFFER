package com.akata.offerservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    @Value("${upload.dir}")
    String upload_dir;

    public String saveDocument(MultipartFile document) throws IOException {
        String file_name = StringUtils.cleanPath(document.getOriginalFilename());
        Path upload_path = Paths.get(upload_dir+"/specifications");
        if(!Files.exists(upload_path)){
            Files.createDirectories(upload_path);
        }
        try (InputStream inputStream = document.getInputStream()){
            Path file_path = upload_path.resolve(file_name);
            Files.copy(inputStream, file_path, StandardCopyOption.REPLACE_EXISTING);
            return file_name;
        }catch (IOException e){
            throw new IOException("Could not save document: "+ file_name +e);
        }
    }

    public Resource loadDocument(String document_name) throws IOException {
        Path upload_path = Paths.get(upload_dir+"/specifications");
        Path document_path = upload_path.resolve(document_name).normalize();
        Resource resource = new UrlResource(document_path.toUri());
        if(resource.exists()){
            return resource;
        }else{
            throw new IOException("Document not found !");
        }
    }
}
