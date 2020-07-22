package com.jiangtj.common.commonmarkspringstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
    // todo
//    private List<AttributeProviderFactory> attributeProviderFactories = new ArrayList<>();
//    private List<HtmlNodeRendererFactory> nodeRendererFactories = new ArrayList<>();
//    private UrlSanitizer urlSanitizer = new DefaultUrlSanitizer();
}
