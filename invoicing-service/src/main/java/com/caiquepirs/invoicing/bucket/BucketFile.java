package com.caiquepirs.invoicing.bucket;

import org.springframework.http.MediaType;

import java.io.InputStream;

public record BucketFile(
        String name,
        InputStream inputStream,
        MediaType mediaType,
        Long size) {
}
