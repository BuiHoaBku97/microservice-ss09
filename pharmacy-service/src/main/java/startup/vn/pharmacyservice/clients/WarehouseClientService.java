package startup.vn.pharmacyservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WarehouseClientService {
    private static final Logger log = LoggerFactory.getLogger(WarehouseClientService.class);
    private final RestTemplate restTemplate;

    public WarehouseClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "warehouseFallback")
    public String checkStock(String productId) {
        String url = "http://localhost:8082/api/v1/warehouse/check?productId=" + productId;
        log.info("Calling warehouse service: {}", url);
        return restTemplate.getForObject(url, String.class);
    }

    // fallback must accept the original parameters plus Throwable
    public String warehouseFallback(String productId, Throwable t) {
        log.warn("Warehouse service unavailable for product {}: {}", productId, t.toString());
        // return a degraded response that avoids blocking checkout
        return "UNKNOWN_AVAILABILITY";
    }
}
