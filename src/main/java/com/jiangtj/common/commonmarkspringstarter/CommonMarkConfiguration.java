package com.jiangtj.common.commonmarkspringstarter;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.parser.block.BlockParserFactory;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/22.
 */
@Configuration
@EnableConfigurationProperties({ Properties.class })
@ConditionalOnProperty(prefix = "commonmark", value = "enabled", havingValue = "true", matchIfMissing = true)
public class CommonMarkConfiguration {

    /**
     * 设置 parser
     * @param properties 配置文件，来自 spring
     * @param extensions 自定义扩展
     * @param blockParsers 自定义块解析
     * @param delimiterProcessors 自定义处理
     * @return Parser
     */
    @Bean
    @ConditionalOnMissingBean
    public Parser parser(Properties properties,
                         List<Extension> extensions,
                         List<BlockParserFactory> blockParsers,
                         List<DelimiterProcessor> delimiterProcessors) {
        Parser.Builder builder = Parser.builder()
                .extensions(extensions);
        if (!CollectionUtils.isEmpty(properties.getEnabledBlockTypes())) {
            builder.enabledBlockTypes(properties.getEnabledBlockTypes());
        }
        blockParsers.forEach(builder::customBlockParserFactory);
        delimiterProcessors.forEach(builder::customDelimiterProcessor);
        return builder.build();
    }

    /**
     * 设置 渲染器
     * @param properties 配置文件，来自 spring
     * @param extensions 自定义扩展
     * @param attributes todo
     * @param htmlNodes todo
     * @return HtmlRenderer
     */
    @Bean
    @ConditionalOnMissingBean
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

    /**
     * Commonmarks
     * @param parser 解析器
     * @param renderer 渲染器
     * @return Commonmarks
     */
    @Bean
    @ConditionalOnMissingBean
    public CommonMark commonMark(Parser parser, HtmlRenderer renderer) {
        return new CommonMark(parser, renderer);
    }

}
