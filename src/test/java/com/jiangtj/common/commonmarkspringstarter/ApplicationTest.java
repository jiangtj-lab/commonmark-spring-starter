package com.jiangtj.common.commonmarkspringstarter;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.*;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/27.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest(classes = {CommonMarkConfiguration.class})
public @interface ApplicationTest {
}
