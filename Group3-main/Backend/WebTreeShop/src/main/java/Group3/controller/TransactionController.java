package Group3.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group3.dto.TransactionResponseDTO;
import Group3.service.TransactionInterface;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private TransactionInterface transServ;

    public TransactionController(TransactionInterface transServ) {
        this.transServ = transServ;
    }
    
    @GetMapping("")
    public ResponseEntity<List<TransactionResponseDTO>> getTransaction() {
        List<TransactionResponseDTO> response = transServ.getTransactionByAccounts();
        return ResponseEntity.ok(response);
    }
    
}
