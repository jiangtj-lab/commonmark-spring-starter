package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
public record CommonMark(Parser parser, HtmlRenderer renderer) {

    /**
     * 渲染 md 内容为 html
     * @param content md内容
     * @return html
     */
    public String render(String content) {
        Node document = parser.parse(content);
        return renderer.render(document);
    }
}
