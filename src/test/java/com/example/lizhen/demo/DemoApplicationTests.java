package com.example.lizhen.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
     Long longCoung = jdbcTemplate.queryForObject("select count(1) from aaaa",Long.class);
     log.info("记录数据："+longCoung);

        log.info("DataSource数据源："+dataSource.getClass());

    }

}
