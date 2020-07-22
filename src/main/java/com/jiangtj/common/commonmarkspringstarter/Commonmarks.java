package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
public class Commonmarks {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public Commonmarks(Parser parser, HtmlRenderer renderer) {
        this.parser = parser;
        this.renderer = renderer;
    }

    public String render(String content) {
        Node document = parser.parse(content);
        return renderer.render(document);
    }
}
