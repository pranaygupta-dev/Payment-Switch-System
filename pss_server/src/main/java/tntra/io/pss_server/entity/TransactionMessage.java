package tntra.io.pss_server.entity;

import lombok.Data;

@Data
public class TransactionMessage {

    private String transactionId;
    private String panNo;
    private String amount;
    private String responseCode;
    private String destination;

    public TransactionMessage(String transactionId, String panNo, String amount, String responseCode, String destination) {
        this.transactionId = transactionId;
        this.panNo = panNo;
        this.amount = amount;
        this.responseCode = responseCode;
        this.destination = destination;
    }

}
