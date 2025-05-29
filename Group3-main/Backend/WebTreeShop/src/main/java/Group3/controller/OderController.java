package Group3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group3.dto.OrderItemResponseDTO;
import Group3.dto.OrderRequestDTO;
import Group3.service.OrderInterface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/order")
public class OderController {
    private OrderInterface orderInterface;

    public OderController(OrderInterface orderInterface) {
        this.orderInterface = orderInterface;
    }

    @PostMapping()
    public ResponseEntity<String> orderAPI(@RequestBody OrderRequestDTO request){
        orderInterface.seviceConfirmOrder(request.getList());
        return ResponseEntity.ok("Email sent");
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemResponseDTO>> getOderItem(@PathVariable UUID orderId) {
        List<OrderItemResponseDTO> response = orderInterface.getOderItem(orderId);
        return ResponseEntity.ok(response);
    }
    
}
