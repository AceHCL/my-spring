package my.framwork.helper;

import my.framwork.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 * 容器助手类
 *
 * @author ace-huang
 * @create 2019-09-25 9:04 PM
 */
public final class BeanHelper {

    /**
     * Spring容器，拥有所有的实例
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();

        //bean实例话，放入bean容器中
        for (Class<?> beanClass: beanClassSet
             ) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     * 获取bean容器
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean的实例
     */
    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean实例
     * @param cls
     * @param obj
     */
    public static  void setBean(Class<?> cls, Object obj){
        BEAN_MAP.put(cls,obj);
    }
}