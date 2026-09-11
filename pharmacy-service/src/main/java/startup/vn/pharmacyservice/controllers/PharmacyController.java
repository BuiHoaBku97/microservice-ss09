package startup.vn.pharmacyservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import startup.vn.pharmacyservice.clients.WarehouseClient;

@RestController
@RequestMapping("/api/v1/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    @Value("${app.branch-name}")
    private String branchName;

    @Value("${app.hotline}")
    private String hotline;

    private final WarehouseClient warehouseClient;

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello from %s Pharmacy! For assistance, call us at %s.", branchName, hotline);
    }

    @GetMapping("/stock/{productId}")
    public String checkStock(@PathVariable String productId) {
        return warehouseClient.getStock(productId);
    }
}
