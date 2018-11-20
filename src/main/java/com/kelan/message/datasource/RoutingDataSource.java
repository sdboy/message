package com.kelan.message.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/20 17:15
 * @see
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceHolder.getDataSourceType();
  }
}
