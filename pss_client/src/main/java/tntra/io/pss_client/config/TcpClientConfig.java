package tntra.io.pss_client.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "switch")
@Configuration
@Data
public class TcpClientConfig {
    private String host;
    private int port;
}
