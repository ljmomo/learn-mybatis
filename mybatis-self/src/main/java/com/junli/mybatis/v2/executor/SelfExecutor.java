package com.junli.mybatis.v2.executor;

import com.junli.mybatis.v2.config.MapperData; /**
 * @author lijun
 * @since 2018-04-03 9:13
 */
public interface SelfExecutor {

    <T> T query(MapperData mapperData, Object paramters);
}
