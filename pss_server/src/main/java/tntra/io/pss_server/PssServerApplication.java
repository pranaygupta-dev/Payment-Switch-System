package tntra.io.pss_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tntra.io.pss_server.service.RouterProperties;
import tntra.io.pss_server.service.ValidationProperties;

@EnableConfigurationProperties({
        RouterProperties.class,
        ValidationProperties.class
})
@SpringBootApplication
public class PssServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PssServerApplication.class, args);
    }

}
