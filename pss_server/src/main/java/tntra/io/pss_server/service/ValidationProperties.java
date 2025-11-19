package tntra.io.pss_server.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "limits")
public class ValidationProperties {

    private int panMinLength;
    private int panMaxLength;

    private int amountMin;
    private int amountMax;
}
