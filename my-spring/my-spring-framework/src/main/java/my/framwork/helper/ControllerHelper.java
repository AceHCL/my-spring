package my.framwork.helper;

import my.framwork.annotation.RequestMapping;
import my.framwork.bean.Handler;
import my.framwork.bean.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-25 9:53 PM
 */
public final class ControllerHelper {


    private static final Map<Request, Handler> REQUEST_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass:controllerClassSet){
                Method [] methods = controllerClass.getDeclaredMethods();

                if (ArrayUtils.isNotEmpty(methods)){
                    for (Method method: methods){
                        if (method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String requestPath = requestMapping.value();
                            String requestMethod = requestMapping.method().name();

                            Request request = new Request(requestMethod,requestPath);
                            Handler handler = new Handler(controllerClass,method);
                            REQUEST_MAP.put(request,handler);
                        }
                    }
                }
            }
        }
    }


    /**
     * 获取 Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return REQUEST_MAP.get(request);
    }
}