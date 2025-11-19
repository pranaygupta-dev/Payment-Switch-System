package tntra.io.pss_server.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "routing")
public class RouterProperties {

    private List<String> bankAEndPoints;
    private List<String> bankBEndPoints;
    private List<String> blacklistedPan;
    private String defaultEndPoint;

}
