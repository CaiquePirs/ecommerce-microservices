package com.caiquepirs.invoicing.bucket;

import com.caiquepirs.invoicing.config.props.MinioProps;
import com.caiquepirs.invoicing.controller.advice.exceptions.InputFileException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final MinioClient client;
    private final MinioProps props;

    public void upload(BucketFile file){
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(props.getBucketName())
                    .object(file.name())
                    .stream(file.inputStream(), file.size(), -1)
                    .contentType(file.mediaType().toString())
                    .build();

            client.putObject(objectArgs);

        } catch (Exception e){
            throw new InputFileException("Error processing file: " + e.getMessage());
        }

    }

    public String getFileUrl(String fileName) {
        try {
            var object = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(props.getBucketName())
                    .object(fileName)
                    .expiry(1, TimeUnit.HOURS)
                    .build();

            return client.getPresignedObjectUrl(object);

        } catch (Exception e) {
            throw new InputFileException("Error processing file: " + e.getMessage());

        }
    }

}
