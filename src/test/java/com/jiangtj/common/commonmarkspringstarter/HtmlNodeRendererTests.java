package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/23.
 */
@SpringBootTest
public class HtmlNodeRendererTests {

    @Resource
    private Commonmarks commonmarks;

    @TestConfiguration
    static class HtmlNodeBean {
        @Bean
        public HtmlNodeRendererFactory indentedCodeBlockNodeRenderer() {
            return IndentedCodeBlockNodeRenderer::new;
        }
    }

    static class IndentedCodeBlockNodeRenderer implements NodeRenderer {
        private final HtmlWriter html;
        IndentedCodeBlockNodeRenderer(HtmlNodeRendererContext context) {
            this.html = context.getWriter();
        }
        @Override
        public Set<Class<? extends Node>> getNodeTypes() {
            // Return the node types we want to use this renderer for.
            return Collections.singleton(IndentedCodeBlock.class);
        }
        @Override
        public void render(Node node) {
            // We only handle one type as per getNodeTypes, so we can just cast it here.
            IndentedCodeBlock codeBlock = (IndentedCodeBlock) node;
            html.line();
            html.tag("pre");
            html.text(codeBlock.getLiteral());
            html.tag("/pre");
            html.line();
        }
    }

    @Test
    void testHtmlNode() {
        String result = commonmarks.render("IndentedCodeBlock: \n" +
                "\n" +
                "    void testHtmlNode() {\n" +
                "      assertEquals(...)\n" +
                "    }\n");
        assertEquals("<p>IndentedCodeBlock:</p>\n" +
                "<pre>void testHtmlNode() {\n" +
                "  assertEquals(...)\n" +
                "}\n" +
                "</pre>\n", result);
    }
}
