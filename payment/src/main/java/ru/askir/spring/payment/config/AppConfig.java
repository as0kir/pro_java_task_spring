package ru.askir.spring.payment.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.askir.spring.payment.exception.ProductErrorHandler;

@Configuration
@EnableConfigurationProperties(PropertyConfig.class)
public class AppConfig {
    private final PropertyConfig propertyConfig;
    private final ProductErrorHandler productErrorHandler;

    public AppConfig(PropertyConfig propertyConfig, ProductErrorHandler productErrorHandler) {
        this.propertyConfig = propertyConfig;
        this.productErrorHandler = productErrorHandler;
    }

    @Bean
    public RestTemplate productRestClient(){
        return new RestTemplateBuilder()
                .rootUri(propertyConfig.getBasePath())
                .errorHandler(productErrorHandler)
                .build();
    }
}
