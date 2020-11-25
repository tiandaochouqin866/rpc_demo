package rpc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RpcDemoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcDemoServerApplication.class, args);
    }
}
