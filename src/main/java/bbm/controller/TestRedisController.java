package bbm.controller;

import bbm.jooq.Tables;
import bbm.model.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bbm
 * @date 2020/5/14
 */
@RestController
@RequestMapping("/redis/api/v1.0")
@Slf4j
public class TestRedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/data/{key}")
    public Object getDbData(@PathVariable String key) {
        log.info("Get Redis Data: {}", key);
        return redisTemplate.boundValueOps(key).get();
    }

    @PostMapping("/data")
    public void addData(@RequestBody RestData data) {
        log.info("Update Redis Data: {}", data);
        redisTemplate.boundValueOps(String.valueOf(data.getId())).set(data.getValue());
    }
}
