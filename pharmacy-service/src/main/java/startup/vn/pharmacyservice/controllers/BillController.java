package startup.vn.pharmacyservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bill")
@RefreshScope
public class BillController {
    @Value("${pharmacy.vat-rate}")
    private double vatRate;

    @PostMapping
    public String createBill() {
        // Logic to create a bill
        return String.format("Bill created with VAT rate: %.2f%%", vatRate );
    }
}
