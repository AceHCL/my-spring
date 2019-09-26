package my.framwork.helper;

import my.framwork.annotation.Aspect;
import my.framwork.proxy.AspectProxy;
import my.framwork.proxy.Proxy;
import my.framwork.proxy.ProxyFactory;
import my.framwork.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-26 1:05 PM
 */
public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
    static {
        try {
            // 切面类和目标类集合的映射
            Map<Class<?>, Set<Class<?>>> aspectMap = createAspectMap();
            //目标类-切面对象列表的映射
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(aspectMap);

            //  切面对象植入到目标类中，创建代理对象
            for (Map.Entry<Class<?>,List<Proxy>> targetEntry:targetMap.entrySet()
                 ) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyFactory.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        }catch (Exception e){
            LOGGER.error("aop failure",e);
        }
    }

    /**
     * 获取切面类-目标类集合的映射
     */
    private static Map<Class<?>, Set<Class<?>>> createAspectMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> aspectMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(aspectMap);
        return aspectMap;
    }

    /**
     *  获取普通切面类-目标类集合的映射
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> aspectMap) throws Exception {
        //所有实现了AspectProxy抽象类的切面
        Set<Class<?>> aspectClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> aspectClass : aspectClassSet) {
            if (aspectClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = aspectClass.getAnnotation(Aspect.class);
                //与该切面对应的目标类集合
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                aspectMap.put(aspectClass, targetClassSet);
            }
        }
    }

    /**
     * 根据@Aspect定义的包名和类名去获取对应的目标类集合
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        // 包名
        String pkg = aspect.pkg();
        // 类名
        String cls = aspect.cls();
        // 如果包名与类名均不为空，则添加指定类
        if (!pkg.equals("") && !cls.equals("")) {
            targetClassSet.add(Class.forName(pkg + "." + cls));
        } else if (!pkg.equals("")) {
            // 如果包名不为空, 类名为空, 则添加该包名下所有类
            targetClassSet.addAll(ClassUtil.getClassSet(pkg));
        }
        return targetClassSet;
    }

    /**
     * 将切面类-目标类集合的映射关系 转化为 目标类-切面对象列表的映射关系
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> aspectMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : aspectMap.entrySet()) {
            //切面类
            Class<?> aspectClass = proxyEntry.getKey();
            //目标类集合
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            //创建目标类-切面对象列表的映射关系
            for (Class<?> targetClass : targetClassSet) {
                //切面对象
                Proxy aspect = (Proxy) aspectClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(aspect);
                } else {
                    //切面对象列表
                    List<Proxy> aspectList = new ArrayList<Proxy>();
                    aspectList.add(aspect);
                    targetMap.put(targetClass, aspectList);
                }
            }
        }
        return targetMap;
    }



}