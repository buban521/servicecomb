package cn.lszz;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class AppProvider {

    public static void main(String[] args) {
        SpringApplication.run(AppProvider.class, args);
    }

}
