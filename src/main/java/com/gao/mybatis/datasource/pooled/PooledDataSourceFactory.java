package com.gao.mybatis.datasource.pooled;

import com.gao.mybatis.datasource.unpooled.UnpooledDataSource;
import com.gao.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;

public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new UnpooledDataSource();
    }
}
