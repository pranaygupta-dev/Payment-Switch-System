package tntra.io.pss_client.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tntra.io.pss_client.config.TcpClientConfig;
import tntra.io.pss_client.service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final TcpClientConfig tcpClientConfig;

    @Override
    public String sendRequest(String jsonRequest) throws Exception {
        String host = tcpClientConfig.getHost();
        int port = tcpClientConfig.getPort();

        //Socket creation takes place
        try(Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(10000);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write((jsonRequest.trim() + "\r\n").getBytes(StandardCharsets.UTF_8));
            outputStream.flush();


            //Reading response from server
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                String response = bufferedReader.readLine();

                return response != null ? response : "";
            }
        }
    }
}
