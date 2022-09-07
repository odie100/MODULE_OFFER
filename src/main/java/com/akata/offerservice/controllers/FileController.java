package com.akata.offerservice.controllers;

import com.akata.offerservice.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(path = "/upload")
    public String upload( @RequestParam("document") MultipartFile file) throws IOException {
        return this.fileStorageService.saveDocument(file);
    }

    @GetMapping(path = "/load/{filename}", produces = MediaType.ALL_VALUE)
    public Resource load (@PathVariable("filename") String file_name) throws IOException {
        return this.fileStorageService.loadDocument(file_name);
    }
}
