package my.framwork.bean;

/**
 * 描述:
 * 封装JSON结果
 *
 * @author ace-huang
 * @create 2019-09-25 10:05 PM
 */
public class Data {

    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}