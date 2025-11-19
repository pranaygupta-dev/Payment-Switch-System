package tntra.io.pss_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import tntra.io.pss_server.service.RouterProperties;
import tntra.io.pss_server.service.ValidationProperties;
import tntra.io.pss_server.service.ValidationService;
import tntra.io.pss_server.entity.TransactionMessage;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final ValidationProperties limits;

    private final RouterProperties routing;

    @Override
    public void validateTransaction(TransactionMessage message) {

        if(message.getTransactionId() == null || message.getTransactionId().isEmpty()){
            message.setResponseCode("01");
            return;
        }

        if(message.getPanNo() == null) {
            message.setResponseCode("02");
            return;
        }

        int panLength = message.getPanNo().length();
        if(panLength < limits.getPanMinLength() || panLength > limits.getPanMaxLength()){
            message.setResponseCode("02");
            return;
        }

        Double amount;
        try {
            amount = Double.parseDouble(message.getAmount());
        } catch (NumberFormatException e) {
            message.setResponseCode("03");
            return;
        }

        if(amount < limits.getAmountMin() || amount > limits.getAmountMax()){
            message.setResponseCode("03");
            return;
        }

        if(routing.getBlacklistedPan().contains(message.getPanNo())){
            message.setResponseCode("04");
            return;
        }

        message.setResponseCode("00");
    }
}
