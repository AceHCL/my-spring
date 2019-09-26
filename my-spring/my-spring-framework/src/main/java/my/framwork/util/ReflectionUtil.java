package my.framwork.util;

import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:
 * 反射工具类
 *
 * @author ace-huang
 * @create 2019-09-25 8:53 PM
 */
public final class ReflectionUtil {

    private static final Logger LOGGER  = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 根据类名创建实例
     * @param className
     * @return
     */
    public static Object newInstance(String className){
        Class<?> cls = ClassUtil.loadClass(className);
        return newInstance(cls);
    }

    public static Object invokeMethod(Object obj, Method method, Object...args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        } catch (Exception e) {
            LOGGER.error("invoke methos failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field,Object value){
        try {
            //去除私有权限
            field.setAccessible(true);
            field.set(obj,value);
        }catch (Exception e){
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}