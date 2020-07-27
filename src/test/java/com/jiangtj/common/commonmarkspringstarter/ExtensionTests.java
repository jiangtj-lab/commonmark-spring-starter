package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.Extension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
@ApplicationTest
@ContextConfiguration(classes = {ExtensionTests.TestBeans.class})
public class ExtensionTests {

    @Resource
    private Commonmarks commonmarks;

    @TestConfiguration
    public static class TestBeans {
        @Bean
        public Extension taskListItemsExtension() {
            return TaskListItemsExtension.create();
        }
    }

    @Test
    void testTaskListItemsExtension() {
        String result = commonmarks.render("- [ ] task #1\n- [x] task #2");
        assertEquals("<ul>\n" +
                "<li><input type=\"checkbox\" disabled=\"\"> task #1</li>\n" +
                "<li><input type=\"checkbox\" disabled=\"\" checked=\"\"> task #2</li>\n" +
                "</ul>\n", result);
    }

}
