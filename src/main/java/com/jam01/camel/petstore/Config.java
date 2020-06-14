package com.jam01.camel.petstore;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public ConnectionFactory containerRabbit() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost");
        cf.setPort(5672);
        cf.setUsername("guest");
        cf.setPassword("guest");
        return cf;
    }

    @Bean
    public DataSource inMemDatabase() {
        DataSource source =
                DataSourceBuilder.create()
                .url("jdbc:h2:mem:test")
                .driverClassName("org.h2.Driver")
                .username("SA")
                .password("")
                .build();

        DatabasePopulatorUtils.execute(new ResourceDatabasePopulator(new ClassPathResource("database/init.ddl")), source);

        return source;
    }
}
