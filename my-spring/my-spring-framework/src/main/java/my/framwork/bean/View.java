package my.framwork.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 封装视图返回结果
 *
 * @author ace-huang
 * @create 2019-09-25 10:06 PM
 */
public class View {

    private String path;

    private Map<String ,Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}