package my.framwork.helper;

import my.framwork.annotation.Autowired;
import my.framwork.util.ReflectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.omg.CORBA.OBJ_ADAPTER;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *  IOC
 *  功能，遍历Bean容器，为所有的自动注入的属性注入实例
 * @author ace-huang
 * @create 2019-09-25 9:13 PM
 */
public class IocHelper {

    static {
        Map<Class<?>, Object> beanMap  = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                //bean的class类，
                Class<?> beanClass = beanEntry.getKey();

                //bean的实例
                Object beanInstance = beanEntry.getValue();

                //获取属性
                Field[] beanFields = beanClass.getDeclaredFields();
                //遍历属性
                if (ArrayUtils.isNotEmpty(beanFields)){
                    for (Field beanField:beanFields){
                        //是否带有Autowired注解
                        if (beanField.isAnnotationPresent(Autowired.class)){
                            //属性类型
                            Class<?> beanFieldClass = beanField.getType();
                            //如果beanFieldClass是接口，获取接口的实现类
                            beanFieldClass = findImplementClass(beanFieldClass);
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldClass);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取对应的实现类
     * @param interfaceClass
     * @return
     */

    private static Class<?> findImplementClass(Class<?> interfaceClass) {

        Class<?> implementClass = interfaceClass;
        Set<Class<?>> classSetBySuper = ClassHelper.getClassSetBySuper(interfaceClass);

        if (CollectionUtils.isEmpty(classSetBySuper)){
            implementClass = classSetBySuper.iterator().next();
        }
        return implementClass;
    }
}