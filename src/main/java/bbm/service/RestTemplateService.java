package bbm.service;

import bbm.model.RestData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author bbm
 * @date 2020/5/15
 */
@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public RestData testRestTemplate(int id) {
        return this.restTemplate.getForObject("http://localhost:8080/api/v1.0/data/" + id, RestData.class);
    }
}
