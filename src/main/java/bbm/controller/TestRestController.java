package bbm.controller;

import bbm.model.RestData;
import bbm.service.RestTemplateService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bbm
 * @date 2020/5/14
 */
@RestController
@RequestMapping("/api/v1.0")
@Slf4j
@Validated
public class TestRestController {

    @Value("${rest.test.value}")
    private String restTestValue;
    @Autowired
    private RestTemplateService restTemplateService;

    private Map<Integer, String> dataMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        dataMap.put(0, restTestValue);
    }

    @GetMapping("/data/{id}")
    public Object getData(@PathVariable int id) {
        log.info("Get Data: {}", id);
        return new RestData(id, dataMap.get(id));
    }

    @GetMapping("/data/{id}/validation")
    public Object getDataWithValidation(@Max(10) @Min(1) @PathVariable int id) {
        log.info("Get Data: {}", id);
        return new RestData(id, dataMap.get(id));
    }

    @PutMapping("/data")
    public void updateData(@RequestBody RestData data) {
        log.info("Update Data: {}", data);
        this.dataMap.put(data.getId(), data.getValue());
    }

    @PostMapping("/data")
    public void addData(@RequestBody RestData data) {
        log.info("Add Data: {}", data);
        this.dataMap.put(data.getId(), data.getValue());
    }

    @DeleteMapping("/data")
    public void deleteData(@RequestParam("id") int id) {
        log.info("Delete Data: {}", id);
        dataMap.remove(id);
    }

    @GetMapping("/exception")
    public void testException() {
        throw new RuntimeException("Test exception");
    }

    @GetMapping("/template/{id}")
    public Object testTemplate(@PathVariable int id) {
        log.info("Test Template: {}", id);
        return restTemplateService.testRestTemplate(id);
    }

}
