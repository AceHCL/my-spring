package my.framwork.annotation;

import sun.jvm.hotspot.tools.HeapDumper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:
 * 处理器方法注解
 *
 * @author ace-huang
 * @create 2019-09-25 7:35 PM
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 请求的路径
     * @return 返回值
     */
    String value() default "";

    RequestMethod method() default RequestMethod.GET;

}