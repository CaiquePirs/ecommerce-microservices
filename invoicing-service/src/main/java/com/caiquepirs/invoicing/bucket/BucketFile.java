package com.caiquepirs.invoicing.bucket;

import lombok.Builder;
import org.springframework.http.MediaType;

import java.io.InputStream;

@Builder
public record BucketFile(
        String name,
        InputStream inputStream,
        MediaType mediaType,
        Long size) {
}
