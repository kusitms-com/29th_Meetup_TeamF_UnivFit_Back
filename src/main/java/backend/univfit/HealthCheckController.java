package backend.univfit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public String hello() {
        return "Hello, UnivFit!";
    }

    @GetMapping("/health-check")
    public String check() {
        return "Health-Check-Sucess";
    }
}

