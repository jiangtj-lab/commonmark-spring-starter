package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.node.Image;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/23.
 */
@ApplicationTest
@ContextConfiguration(classes = {AttributeProviderTests.AttributeBean.class})
public class AttributeProviderTests {

    @Resource
    private CommonMark commonMark;

    @TestConfiguration
    static class AttributeBean {
        @Bean
        public AttributeProviderFactory imageAttributeProvider() {
            return context -> new ImageAttributeProvider();
        }
    }

    static class ImageAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Image) {
                attributes.put("class", "border");
            }
        }
    }

    @Test
    void testAttributes() {
        String result = commonMark.render("![](https://example.com/pic.jpg)");
        assertEquals("<p><img src=\"https://example.com/pic.jpg\" alt=\"\" class=\"border\" /></p>\n", result);
    }
}
