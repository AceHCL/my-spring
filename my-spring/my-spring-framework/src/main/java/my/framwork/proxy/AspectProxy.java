package my.framwork.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 描述:
 * 切面抽象类
 *
 * @author ace-huang
 * @create 2019-09-26 12:53 PM
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {

        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object [] params = proxyChain.getMethodParams();
        begin();
        try {
            if (intercept(method,params)){
                before(method,params);
                result = proxyChain.doProxyChain();
                after(method,params);
            }else{
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure",e);
            error(method,params,e);
            throw e;
        }finally {
            end();
        }
        return result;
    }

    public void begin(){

    }
    public boolean intercept(Method method,Object[] params)throws Throwable{
        return true;
    }

    /**
     * 前置增强
     */
    public void before(Method method, Object[] params) throws Throwable {
    }

    /**
     * 后置增强
     */
    public void after(Method method, Object[] params) throws Throwable {
    }

    /**
     * 异常增强
     */
    public void error(Method method, Object[] params, Throwable e) {
    }

    /**
     * 最终增强
     */
    public void end() {
    }

}