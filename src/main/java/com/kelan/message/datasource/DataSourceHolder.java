package com.kelan.message.datasource;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/20 18:48
 * @see
 */
public class DataSourceHolder {
  private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
  /**
   * 设置数据源类型
   * @method setDataSourceType
   * @author JG
   * @date 2018/11/20 18:49
   * @param dataSourceType 数据库类型
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public static void setDataSourceType(String dataSourceType) {
    contextHolder.set(dataSourceType);
  }
  
  /**
   * 获取数据源类型
   * @method getDataSourceType
   * @author JG
   * @date 2018/11/20 18:50
   * @return java.lang.String
   * @since V1.0.0
   * @version V1.0.0
   */
  public static String getDataSourceType() {
    return contextHolder.get();
  }
  /**
   * 清除数据源类型
   * @method clearDataSourceType
   * @author JG
   * @date 2018/11/20 18:50
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public static void clearDataSourceType() {
    contextHolder.remove();
  }
}
