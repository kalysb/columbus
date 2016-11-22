package com.rbkmoney.columbus;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.DockerPort;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import com.palantir.docker.compose.logging.FileLogCollector;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.rules.ExternalResource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.File;

@TestConfiguration
public class IntegrationBaseRule extends ExternalResource {

    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("src/test/resources/docker-compose.yml")
            .logCollector(new FileLogCollector(new File("target/pglog")))
            .waitingForService("postgres", HealthChecks.toHaveAllPortsOpen())
            .build();


    @Bean
    public DataSource dataSource() {
        DockerPort postgres = docker.containers().container("postgres").port(5432);

        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .username("postgres")
                .password("postgres")
                .driverClassName("org.postgresql.Driver")
                .url(postgres.inFormat("jdbc:postgresql://$HOST:$EXTERNAL_PORT/postgres"))
                .build();
    }

    protected void before() throws Throwable {
        docker.before();
    }

    protected void after() {
       docker.after();
    }


    public DockerComposeRule getDockerComposeRule(){
        return docker;
    }
}
