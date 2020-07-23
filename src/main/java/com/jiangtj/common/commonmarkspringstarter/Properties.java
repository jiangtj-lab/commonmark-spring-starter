package com.jiangtj.common.commonmarkspringstarter;

import lombok.Data;
import org.commonmark.node.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
@Data
@ConfigurationProperties("commonmark")
public class Properties {
    private String softbreak = "\n";
    private boolean escapeHtml = false;
    private boolean sanitizeUrls = false;
    private boolean percentEncodeUrls = false;

    /**
     * Describe the list of markdown features the parser will recognize and parse.
     * <p>
     * By default, CommonMark will recognize and parse the following set of "block" elements:
     * <ul>
     * <li>{@link Heading} ({@code #})
     * <li>{@link HtmlBlock} ({@code <html></html>})
     * <li>{@link ThematicBreak} (Horizontal Rule) ({@code ---})
     * <li>{@link FencedCodeBlock} ({@code ```})
     * <li>{@link IndentedCodeBlock}
     * <li>{@link BlockQuote} ({@code >})
     * <li>{@link ListBlock} (Ordered / Unordered List) ({@code 1. / *})
     * </ul>
     */
    private Set<Class<? extends Block>> enabledBlockTypes;
}
