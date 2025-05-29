package Group3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import Group3.dto.TransactionResponseDTO;
import Group3.exception.EmailNotFoundException;
import Group3.model.Accounts;
import Group3.model.Transaction;
import Group3.repository.AccountsRepository;
import Group3.repository.TransactionRepository;
import Group3.utility.SecurityUtil;

@Service
public class TransactionService implements TransactionInterface {
    private AccountsRepository accRepo;
    private TransactionRepository transRepo;
    
    
    public TransactionService(AccountsRepository accRepo, TransactionRepository transRepo) {
        this.accRepo = accRepo;
        this.transRepo = transRepo;
    }


    @Override
    public List<TransactionResponseDTO> getTransactionByAccounts() {
        UUID accUuid = SecurityUtil.getCurrentUserId();
        Accounts accounts = accRepo.findById(accUuid).orElseThrow(
            () -> new EmailNotFoundException("Account id:"+ accUuid+ " not found")
        );
        List<Transaction> response=transRepo.findByAccount(accounts);
        return response.stream()
        .map(this::mapTransactionDTO)
        .toList();
    }
    private TransactionResponseDTO mapTransactionDTO(Transaction transaction){
        TransactionResponseDTO responseDTO = TransactionResponseDTO.builder()
        .id(transaction.getTransactionID())
        .amount(transaction.getAmount())
        .payMethod(transaction.getPayMethod())
        .state(transaction.getState())
        .time(transaction.getTime())
        .order(transaction.getOrder().getOrderId())
        .build();
        return responseDTO;
    }

}
