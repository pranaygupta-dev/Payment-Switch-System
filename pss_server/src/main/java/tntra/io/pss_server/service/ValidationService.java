package tntra.io.pss_server.service;

import tntra.io.pss_server.entity.TransactionMessage;

public interface ValidationService {
    void validateTransaction(TransactionMessage message);
}
