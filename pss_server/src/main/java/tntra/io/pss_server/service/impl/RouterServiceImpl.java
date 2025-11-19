package tntra.io.pss_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import tntra.io.pss_server.service.RouterProperties;
import tntra.io.pss_server.service.RouterService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouterServiceImpl implements RouterService {

    private final RouterProperties routing;

    @Override
    public String routeDestination(String pan) {
        if(pan == null || pan.length() < 6){
            return "Invalid PAN";
        }

        String bin = pan.substring(0, 6);

        for(String bankA : routing.getBankAEndPoints()){
            if(bin.startsWith(bankA)){
                return "Bank-A";
            }
        }

        for(String bankB : routing.getBankBEndPoints()){
            if(bin.startsWith(bankB)){
                return "Bank-B";
            }
        }

        return routing.getDefaultEndPoint();
    }
}
