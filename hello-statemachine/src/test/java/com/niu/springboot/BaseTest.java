package com.niu.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试基类
 *
 * @author [nza]
 * @version 1.0 [2021/06/10 15:58]
 * @createTime [2021/06/10 15:58]
 */
@SpringBootTest
@Slf4j
class BaseTest {
    @Test
    void testRun() {
        log.info("run test.....");
    }
}
