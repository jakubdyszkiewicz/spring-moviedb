package org.jakubd.moviedb.system.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class HealthCheckController {

    @GetMapping("/api/health")
    public Map<String, String> checkHealth() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }
}
