package my.framwork.proxy;

/**
 * 描述:
 * 代理接口
 *
 * @author ace-huang
 * @create 2019-09-26 11:45 AM
 */
public interface Proxy {

    /**
     * 执行链式处理，将多个代理通过一个链子串起来执行，解决多重代理的问题
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain)throws Throwable;

}