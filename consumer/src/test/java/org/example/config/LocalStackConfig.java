package org.example.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class LocalStackConfig {

    private static final LocalStackContainer.Service[] SERVICES = {
        LocalStackContainer.Service.SQS
    };

    private final String LOCALSTACK_IMAGE = "localstack/localstack";

    @Bean(initMethod = "start", destroyMethod = "stop")
    public LocalStackContainer localStackContainer() {
        LocalStackContainer localStackContainer = new LocalStackContainer(DockerImageName.parse(LOCALSTACK_IMAGE))
            .withServices(SERVICES)
            .withEnv("HOSTNAME_EXTERNAL", "localhost");
        localStackContainer.start();

        return localStackContainer;
    }

}
