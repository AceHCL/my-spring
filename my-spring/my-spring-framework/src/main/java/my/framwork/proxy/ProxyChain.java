package my.framwork.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *  代理链类，proxyList存储代理列表，执行doproxyChain，方法是，按照顺序执行，最后执行目标方法
 * @author ace-huang
 * @create 2019-09-26 11:47 AM
 */
public class ProxyChain {


    //目标类
    private final Class<?> targetClass;
    //目标对象
    private final Object targerObject;
    //目标方法
    private final Method targetMethod;
    //方法代理
    private final MethodProxy methodProxy;
    //方法参数
    private final Object[] methodParams;

    //代理列表
    private List<Proxy> proxyList = new ArrayList<Proxy>();
    //代理的索引
    private  int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass, Object targerObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targerObject = targerObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain()throws Throwable{
        Object methodResult;
        if (proxyIndex< proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else{
            methodResult = methodProxy.invokeSuper(targerObject,methodParams);
        }
        return methodResult;
    }




}