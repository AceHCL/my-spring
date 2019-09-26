package my.framwork;

import my.framwork.helper.*;
import my.framwork.util.ClassUtil;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-25 10:17 PM
 */
public final class HelperLoader {


    public static void init(){
        Class<?> [] classes = {ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> cls: classes
             ) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}