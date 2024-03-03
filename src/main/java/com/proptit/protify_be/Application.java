package com.proptit.protify_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner cmdRunner() {
//        return (args) -> {
//            SecretKey key = Jwts.SIG.HS256.key().build();
//            System.out.println("-clgt-");
//            System.out.println(Encoders.BASE64.encode(key.getEncoded()));
//        };
//    }

}
