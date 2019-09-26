package my.framwork;

import com.sun.tools.corba.se.idl.StringGen;

/**
 * 描述:
 * 配置文件的常量接口，维护配置文件相关的配置项名称
 *
 * @author ace-huang
 * @create 2019-09-25 7:42 PM
 */
public interface ConfigConstant {

    String CONFIG_FILE = "my.properties";

    /**
     * 数据源的配置
     */
    String JDBC_DRIVER = "my.framework.jdbc.driver";
    String JDBC_URL = "my.framework.jdbc.url";
    String JDBC_USERNAME = "my.framework.jdbc.username";
    String JDBC_PASSWORD = "my.framework.jdbc.password";

    /**
     * java源码的地址
     */
    String APP_BASE_PACKAGE = "my.framework.app.base_package";

    /**
     * jsp页面路径
     */
    String APP_JSP_PATH = "my.framework.app.jsp_path";
    /**
     * 静态资源的路径
     */
    String APP_ASSET_PATH = "my.framework.app.asset_path";

}