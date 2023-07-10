package top.remained.silence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lx
 * @CreateTime: 2023-07-11  00:04
 * @Description: TODO
 */
@SpringBootApplication
@ComponentScan("top.remained")
public class ServiceAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }
}

