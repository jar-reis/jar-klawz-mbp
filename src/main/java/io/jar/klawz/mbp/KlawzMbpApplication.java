package io.jar.klawz.mbp;

import io.jar.klawz.starter.KlawzCoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KlawzCoreAutoConfiguration.class)
public class KlawzMbpApplication {
    public static void main(String[] args) {
        SpringApplication.run(KlawzMbpApplication.class, args);
    }
}
