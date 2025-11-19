package tntra.io.pss_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.messaging.MessageChannel;

@Configuration
@IntegrationComponentScan
public class TcpServerConfig {

    @Value("${switch.port}")
    private int port;

    @Bean
    public TcpNetServerConnectionFactory serverConnectionFactory() {

        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(port);
        ByteArrayCrLfSerializer serializer = new ByteArrayCrLfSerializer();
        factory.setSerializer(serializer);
        factory.setDeserializer(serializer);

        return factory;
    }

    @Bean
    //This is where incoming tcp messages will be sent
    public MessageChannel tcpChannel() {
        return new DirectChannel();
    }

    @Bean
    //Accepts raw tcp requests
    public TcpInboundGateway inboundGateway(TcpNetServerConnectionFactory factory) {

        TcpInboundGateway inboundGateway = new TcpInboundGateway(); // Create the inbound gateway
        inboundGateway.setConnectionFactory(factory); // Attach the previously created TCP server to gateway
        inboundGateway.setRequestChannel(tcpChannel()); // Sends the incoming message into tcpChannel
        return inboundGateway;
    }

}
