package my.framwork.bean;

import java.lang.reflect.Method;

/**
 * 描述:
 * 处理器，封装Controller的Class对象和Method方法
 *
 * @author ace-huang
 * @create 2019-09-25 9:51 PM
 */
public class Handler {

    private Class<?> controllerClass;

    private Method controllerMethod;

    public Handler(Class<?> controllerClass, Method controllerMethod) {
        this.controllerClass = controllerClass;
        this.controllerMethod = controllerMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getControllerMethod() {
        return controllerMethod;
    }
}