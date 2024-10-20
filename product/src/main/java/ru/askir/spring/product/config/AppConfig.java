package ru.askir.spring.product.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(PropertyConfig.class)
public class AppConfig {
    private final PropertyConfig propertyConfig;

    public AppConfig(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    @Bean
    public DataSource getDataSource(){
        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName(propertyConfig.getClassName());
        config.addDataSourceProperty("serverName", propertyConfig.getServerName());
        config.addDataSourceProperty("databaseName", propertyConfig.getDatabaseName());
        config.addDataSourceProperty("portNumber", propertyConfig.getPortNumber());
        config.addDataSourceProperty("user", propertyConfig.getUser());
        config.addDataSourceProperty("password", propertyConfig.getPassword());

        return new HikariDataSource(config);
    }

}
