package com.example.tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class Controller {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    private final RestTemplate restTemplate;

    public Controller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/info")
    public ResponseEntity<String> testInfo() {
        LOG.info("TEST INFO");
        return ResponseEntity.ok("TEST INFO");
    }

    @GetMapping("/warn")
    public ResponseEntity<String> testWarn() {
        LOG.warn("TEST WARN");
        return ResponseEntity.ok("TEST WARN");
    }

    @GetMapping("/exception")
    public ResponseEntity<String> testEx() {
        LOG.error("TEST EXCEPTION");
        throw new RuntimeException("TEST EXCEPTION");
    }

    @GetMapping("/rest")
    public ResponseEntity<String> testRest() {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/test-info", String.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
