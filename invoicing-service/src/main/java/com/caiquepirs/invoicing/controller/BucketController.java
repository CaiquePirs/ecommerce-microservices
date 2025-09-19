package com.caiquepirs.invoicing.controller;

import com.caiquepirs.invoicing.bucket.BucketFile;
import com.caiquepirs.invoicing.bucket.BucketService;
import com.caiquepirs.invoicing.controller.advice.exceptions.InputFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/api/invoicing/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @PostMapping
    public ResponseEntity<Void> uploadFile(@RequestParam(name = "file") MultipartFile file){
        try(InputStream is = file.getInputStream()) {

            MediaType type = MediaType.parseMediaType(Objects.requireNonNull(file.getContentType()));
            var bucketFile = new BucketFile(file.getOriginalFilename(), is, type, file.getSize());

            service.upload(bucketFile);
            return ResponseEntity.noContent().build();

        } catch (Exception e){
            throw new InputFileException("Error processing file: " + e.getMessage());
        }

    }


}
