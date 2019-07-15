package demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class TestConfig {
    @Bean
    @Qualifier("THREAD_EXECUTOR")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(5);
    }
}
