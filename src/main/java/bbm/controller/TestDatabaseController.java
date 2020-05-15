package bbm.controller;

import bbm.jooq.Tables;
import bbm.jooq.tables.pojos.Test;
import bbm.model.RestData;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/db/api/v1.0")
@Slf4j
public class TestDatabaseController {

    @Autowired
    private DSLContext dslContext;

    @GetMapping("/data/{id}")
    @Cacheable(value = "DB-Data", key = "#id")
    public Object getDbData(@PathVariable int id) {
        log.info("Get DB Data: {}", id);
        Test test = dslContext
            .selectFrom(Tables.TEST_)
            .where(Tables.TEST_.ID.eq(id))
            .fetchOneInto(Test.class);
        return new RestData(id, test != null ? test.getValue() : null);
    }

    @PutMapping("/data")
    @CachePut(value = "DB-Data", key = "#data.id")
    public Object updateData(@RequestBody RestData data) {
        log.info("Update DB Data: {}", data);
        dslContext
            .update(Tables.TEST_)
            .set(Tables.TEST_.VALUE, data.getValue())
            .where(Tables.TEST_.ID.eq(data.getId()))
            .execute();
        return data;
    }

    @PostMapping("/data")
    @CachePut(value = "DB-Data", key = "#data.id")
    public Object addData(@RequestBody RestData data) {
        log.info("Add Data: {}", data);
        dslContext
            .insertInto(Tables.TEST_)
            .columns(Tables.TEST_.ID, Tables.TEST_.VALUE)
            .values(data.getId(), data.getValue())
            .execute();
        return data;
    }

    @DeleteMapping("/data")
    @CacheEvict(value = "DB-Data", key = "#id")
    public void deleteData(@RequestParam("id") int id) {
        log.info("Delete Data: {}", id);
        dslContext
            .deleteFrom(Tables.TEST_)
            .where(Tables.TEST_.ID.eq(id))
            .execute();
    }

    @PostMapping("/transaction")
    @Transactional(rollbackFor = {Throwable.class})
    public void addDataTransaction(@RequestBody RestData data) {
        log.info("Add Data: {}", data);
        dslContext
            .insertInto(Tables.TEST_)
            .columns(Tables.TEST_.ID, Tables.TEST_.VALUE)
            .values(data.getId(), data.getValue())
            .execute();
        throw new RuntimeException("Test exception abort transaction");
    }
}
