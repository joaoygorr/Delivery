package com.br.burguer.configuration.database;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataInitializaionConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializaionConfig.class);

    private final DataSource dataSource;

    @Autowired
    public DataInitializaionConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        try (Connection connection = this.dataSource.getConnection()){
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("script/data.sql"));

            logger.info("DatabaseInitializer.init | Success run script in database");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute data.sql", e);
        }
    }
}
