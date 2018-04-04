package com.junli.mybatis.v2.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijun
 * @since 2018-04-03 10:31
 */
public class MapperRegistory {

    private  MapperData  mapperData;

    /**
     * key  namespace    MapperData sql 和 返回类型
     */
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    public MapperData get(String nameSpace) {
        return methodSqlMapping.get(nameSpace);
    }

    public MapperData getMapperData() {
        return mapperData;
    }

    public void setMapperData(MapperData mapperData) {
        this.mapperData = mapperData;
    }
}


