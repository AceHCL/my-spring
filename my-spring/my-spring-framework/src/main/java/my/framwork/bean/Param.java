package my.framwork.bean;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 描述:
 * 封装Controller方法的参数
 *
 * @author ace-huang
 * @create 2019-09-25 10:03 PM
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param() {
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty(){
        return MapUtils.isEmpty(paramMap);
    }
}