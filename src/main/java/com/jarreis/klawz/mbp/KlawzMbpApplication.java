package io.jar.klawz.mbp;

import io.jar.klawz.starter.KlawzAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KlawzAutoConfiguration.class)
public class KlawzMbpApplication {

    public static void main(String[] args) {
        SpringApplication.run(KlawzMbpApplication.class, args);
    }
}
