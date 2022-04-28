package com.jiangtj.common.commonmarkspringstarter;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApplicationTest
class CommonmarkSpringStarterApplicationTests {

    @Resource
    private CommonMark commonMark;

    @Test
    void contextLoads() {
        assertNotNull(commonMark);
    }

    @Test
    void testCommonmarksRender() {
        String result = commonMark.render("This is *Sparta*");
        assertEquals("<p>This is <em>Sparta</em></p>\n", result);
    }

}
