package my.framwork.helper;

import my.framwork.bean.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 解析http请求的参数封装在param中
 *
 * @author ace-huang
 * @create 2019-09-25 10:09 PM
 */
public class RequestHelper {

    public static Param createParam(HttpServletRequest request){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        if (!paramNames.hasMoreElements()){
            return null;
        }
        while (paramNames.hasMoreElements()){
            String fieldName = paramNames.nextElement();
            String fieldValue = request.getParameter(fieldName);
            paramMap.put(fieldName,fieldValue);
        }
        return new Param(paramMap);
    }





}