package tntra.io.pss_server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import tntra.io.pss_server.entity.TransactionMessage;
import tntra.io.pss_server.service.RouterService;
import tntra.io.pss_server.service.ValidationService;

@Component
@RequiredArgsConstructor
public class TcpMessageHandler {

    private final ValidationService validationService;

    private final RouterService routerService;

    private final ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = "tcpChannel")
    public String handleMessage(Message<?> message) throws MessagingException, JsonProcessingException {
        try {
            String payload = new String((byte[]) message.getPayload());
            System.out.println("Received message in bytes : " + message);

            if(payload.isEmpty() || !payload.startsWith("{")){
                throw new MessagingException("JSON format error: " + payload);
            }

            TransactionMessage objPayload = objectMapper.readValue(payload, TransactionMessage.class);

            validationService.validateTransaction(objPayload);

            String objPan = objPayload.getPanNo();
            String destination = routerService.routeDestination(objPan);
            objPayload.setDestination(destination);
            System.out.println("Routed to destination: " + destination);

            String responseString = objectMapper.writeValueAsString(objPayload);
            return responseString;
        } catch (Exception e) {
            throw new MessagingException("Error handling message: " + e.getMessage(), e);
        }
    }

}
