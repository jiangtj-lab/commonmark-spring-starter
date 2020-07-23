package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
@Configuration
@EnableConfigurationProperties({ Properties.class })
public class CommonmarkConfiguration {

    @Bean
    public Parser parser(Properties properties,
                         List<Extension> extensions) {
        return Parser.builder()
                .extensions(extensions)
                .build();
    }

    @Bean
    public HtmlRenderer renderer(Properties properties,
                                 List<Extension> extensions,
                                 List<AttributeProviderFactory> attributes,
                                 List<HtmlNodeRendererFactory> htmlNodes) {
        HtmlRenderer.Builder builder = HtmlRenderer.builder()
                .softbreak(properties.getSoftbreak())
                .escapeHtml(properties.isEscapeHtml())
                .sanitizeUrls(properties.isSanitizeUrls())
                .percentEncodeUrls(properties.isPercentEncodeUrls())
                .extensions(extensions);
        attributes.forEach(builder::attributeProviderFactory);
        htmlNodes.forEach(builder::nodeRendererFactory);
        return builder.build();
    }

    @Bean
    public Commonmarks commonmarks(Parser parser, HtmlRenderer renderer) {
        return new Commonmarks(parser, renderer);
    }

}
