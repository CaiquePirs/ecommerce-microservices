package com.caiquepirs.invoicing.controller;

import com.caiquepirs.invoicing.bucket.BucketFile;
import com.caiquepirs.invoicing.bucket.BucketService;
import com.caiquepirs.invoicing.controller.advice.exceptions.InputFileException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;
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

    @GetMapping
    public ResponseEntity<String> getFileUrl(@RequestParam String fileName) {
        try {
            String fileUrl = service.getFileUrl(fileName);

            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                    .location(URI.create(fileUrl))
                    .build();

        } catch (Exception e) {
            throw new InputFileException("Error processing file: " + e.getMessage());
        }
    }

}
