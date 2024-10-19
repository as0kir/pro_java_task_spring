package ru.askir.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource getDataSource(){
        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("databaseName", "test_pro");
        config.addDataSourceProperty("portNumber", "5433");
        config.addDataSourceProperty("user", "tst_user");
        config.addDataSourceProperty("password", "tst_pwd");

        return new HikariDataSource(config);
    }

}
