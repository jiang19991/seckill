package com.nowcoder.seckill;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description Log4j测试
 * @Author Jiang
 * @Date 2021/12/31 17:32
 * @Version 1.0.0
 */
//@SpringBootTest
//@Slf4j
public class Log4j2Test {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2Test.class);
    private static final Logger sucLogger = LoggerFactory.getLogger("task_suc");
    private static final Logger failLogger = LoggerFactory.getLogger("task_fail");

    @Test
    void testLog() {
        sucLogger.debug("task_suc debug");
        sucLogger.info("task_suc info");
        sucLogger.warn("task_suc warn");
        sucLogger.error("task_suc error");


        logger.debug("log debug");
        logger.info("log info");
        logger.warn("log warn");
        logger.error("log error");

        failLogger.debug("task_fail debug");
        failLogger.info("task_fail info");
        failLogger.warn("task_fail warn");
        failLogger.error("task_fail error");


//        log.debug("log debug");
//        log.info("log info");
//        log.warn("log warn");
//        log.error("log error");
    }

}
