package my.framwork.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-26 12:59 PM
 */
public class ProxyFactory {

    /**
     * 一个目标类和一组proxy接口实现，输出一个代理对象
     * @param targetClass
     * @param proxyList
     * @param <T>
     * @return
     */
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,o,targetMethod,methodProxy,methodParams,proxyList).doProxyChain();

            }
        });
    }

}