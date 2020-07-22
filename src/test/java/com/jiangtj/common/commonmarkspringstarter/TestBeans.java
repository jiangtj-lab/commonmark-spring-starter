package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.Extension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
@TestConfiguration
public class TestBeans {
    @Bean
    public Extension taskListItemsExtension() {
        return TaskListItemsExtension.create();
    }
}
