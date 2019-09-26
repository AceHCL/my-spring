package my.framwork.bean;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-25 9:46 PM
 */
public class Request {

    private String requestMethod;

    private String requestpath;

    public Request(String requestMethod, String requestpath) {
        this.requestMethod = requestMethod;
        this.requestpath = requestpath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestpath() {
        return requestpath;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + requestMethod.hashCode();
        result  =31 * result + requestpath.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Request)){
            return false;
        }
        Request request = (Request)obj;
        return request.getRequestpath().equals(this.requestpath) && request.getRequestMethod().equals(this.getRequestMethod());
    }
}