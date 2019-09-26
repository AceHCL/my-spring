package my.framwork.annotation;

import org.omg.CORBA.StringHolder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:
 * 用于切面的注解
 *
 * @author ace-huang
 * @create 2019-09-26 11:39 AM
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 指定用于切面的包名
     * @return
     */
    String pkg() default "";

    /**
     * 指定类名
     * @return
     */

    String cls() default "";

}