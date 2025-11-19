package tntra.io.pss_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionMessage {

    private String transactionId;
    private String panNo;
    private String amount;
    private String responseCode;
    private String destination;

    public TransactionMessage(String transactionId, String panNo, String amount) {
        this.transactionId = transactionId;
        this.panNo = panNo;
        this.amount = amount;
    }

}
