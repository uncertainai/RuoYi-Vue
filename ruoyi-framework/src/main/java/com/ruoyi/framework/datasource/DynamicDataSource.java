package com.ruoyi.framework.datasource;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 
 * @author ruoyi
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
    private Map<Object, Object> targetDataSources;

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources)
    {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey()
    {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

    public Map<Object, Object> getTargetDataSources()
    {
        return targetDataSources;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return (DataSource) super.determineTargetDataSource();
    }
}