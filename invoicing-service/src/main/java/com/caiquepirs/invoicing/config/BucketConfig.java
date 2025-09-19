package com.caiquepirs.invoicing.config;

import com.caiquepirs.invoicing.config.props.MinioProps;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {

    @Autowired
    private MinioProps minioProps;

    @Bean
    public MinioClient bucketClient(){
        return MinioClient.builder()
                .endpoint(minioProps.getUrl())
                .credentials(minioProps.getAccessKey(), minioProps.getSecretKey())
                .build();
    }

}
