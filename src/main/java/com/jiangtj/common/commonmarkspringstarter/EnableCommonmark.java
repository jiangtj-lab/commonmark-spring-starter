package com.jiangtj.common.commonmarkspringstarter;

import org.springframework.context.annotation.Import;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2020/7/23.
 */
@Import({ CommonmarkConfiguration.class })
public @interface EnableCommonmark {
}
