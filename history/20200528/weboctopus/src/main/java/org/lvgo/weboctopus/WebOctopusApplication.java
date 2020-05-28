package org.lvgo.weboctopus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lvgorice@gmail.com
 */
@SpringBootApplication
@MapperScan("org.lvgo.weboctopus.**.mapper")
@ComponentScan("org.lvgo.weboctopus.*")
public class WebOctopusApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebOctopusApplication.class, args);
    }

}
