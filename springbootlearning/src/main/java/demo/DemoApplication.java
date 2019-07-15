package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
 @EnableSwagger2
//@EnableAutoConfiguration
//@ComponentScan
public class DemoApplication {
  public static void main(String args[]) {
    SpringApplication.run(DemoApplication.class, args);
  }


    @Bean

    ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfo(

                "Spring boot demos",

                "Spring boot learning and practice",

                "1.0.0",

                "",

                "",

                "",

                "" );

        return apiInfo;

    }



    @Bean

    public Docket customImplementation(){

        return new Docket(DocumentationType.SWAGGER_2)

                .select()

                .build()

                .apiInfo(apiInfo());

    }
}

