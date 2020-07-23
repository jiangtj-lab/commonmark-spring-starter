package com.jiangtj.common.commonmarkspringstarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/23.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ CommonmarkConfiguration.class })
public @interface EnableCommonmark {
}
