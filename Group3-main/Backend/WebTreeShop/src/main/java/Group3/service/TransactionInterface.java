package Group3.service;

import java.util.List;

import Group3.dto.TransactionResponseDTO;

public interface TransactionInterface {
    List<TransactionResponseDTO> getTransactionByAccounts();
}
