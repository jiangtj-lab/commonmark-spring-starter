# Commonmark Spring Starter
A spring boot starter wapper for commonmark

## Install

Add the following configuration in your pom file

```xml
<!-- add this in root element -->
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-jiangtj-maven</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/jiangtj/maven</url>
    </repository>
</repositories>

<!-- add this in root dependencies -->
<dependency>
    <groupId>com.jiangtj.common</groupId>
    <artifactId>commonmark-spring-starter</artifactId>
    <version>0.0.9</version>
</dependency>
```

## Usage

Step1: enable automatic configuration
```java
@EnableCommonmark
public class ApplicationOrConfiguration {
}
```

Step2: We provide Parser and HtmlRenderer instance, you can easily inject

```java
@Resource
private Parser parser;
@Resource
private HtmlRenderer htmlRenderer;

Node document = parser.parse("This is *Sparta*");
renderer.render(document);
```

Step3: Normally, we don't care about the middle process, so you can use Commonmarks simplified

```java
@Resource
private Commonmarks commonmarks;

commonmarks.render(document); // == parser.parse() and renderer.render()
```

## Custom

Here are some default configurations for the HtmlRenderer, which you can override in the configuration file if needed.

```properties
commonmark.softbreak=\\n
commonmark.escapeHtml=false
commonmark.sanitizeUrls=false
commonmark.percentEncodeUrls=false
```

If there is an instance of AttributeProviderFactory or HtmlNodeRendererFactory, it will be automatically configured

```java
@Configuration
class AttributeBean {
    @Bean
    public AttributeProviderFactory imageAttributeProvider() {
        return context -> new AttributeProviderTests.ImageAttributeProvider();
    }
}

class ImageAttributeProvider implements AttributeProvider {
    @Override
    public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
        if (node instanceof Image) {
            attributes.put("class", "border");
        }
    }
}
```

Extension is the same, only need to provide an extended instance.

```xml
<dependency>
    <groupId>com.atlassian.commonmark</groupId>
    <artifactId>commonmark-ext-task-list-items</artifactId>
    <version>0.15.2</version>
</dependency>
```

```java
@Configuration
public static class ExtensionBeans {
    @Bean
    public Extension taskListItemsExtension() {
        return TaskListItemsExtension.create();
    }
}
```

You can see how to use it in [the test case](https://github.com/jiangtj-lab/commonmark-spring-starter/tree/master/src/test/java/com/jiangtj/common/commonmarkspringstarter)
