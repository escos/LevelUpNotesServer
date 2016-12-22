package ru.levelup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ru.levelup"})
public class AppConfig {
    private Environment env;

    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Bean
    public Integer wsPort() {
        return Integer.parseInt(env.getProperty("app.wsPort"));
    }

    @Bean
    public String mongoHost() {
        return env.getProperty("app.mongoHost");
    }

    @Bean
    public String mongoDBName() {
        return env.getProperty("app.mongoDBName");
    }

}
