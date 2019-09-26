package my.framwork;

import com.alibaba.fastjson.JSON;
import my.framwork.bean.Data;
import my.framwork.bean.Handler;
import my.framwork.bean.Param;
import my.framwork.bean.View;
import my.framwork.helper.BeanHelper;
import my.framwork.helper.ConfigHelper;
import my.framwork.helper.ControllerHelper;
import my.framwork.helper.RequestHelper;
import my.framwork.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 描述:
 * 前端控制器
 *
 * @author ace-huang
 * @create 2019-09-25 10:20 PM
 */

@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();

        /**
         * 获取ServletContext对象，注册Servlet
         */
        ServletContext servletContext = config.getServletContext();
        /**
         * 注册处理jsp和静态资源的servlet
         */
        registerServlet(servletContext);
    }

    /**
     * DefaultServlet he JspServlet 由web容器创建
     * org.apache.catalia,servlets,DefaultSevlet
     * org.apache.jasper.servlet.JspServelt
     * @param servletContext
     */
    private void registerServlet(ServletContext servletContext) {
        //动态注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //动态处理静态资源的默认servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toUpperCase();
        String requestPath = req.getPathInfo();

        //根据tomcat的配置路径有两种情况， "/userList",/context地址/userList".
        String[] splits = requestPath.split("/");
        if (splits.length > 2){
            requestPath = "/" + splits[2];
        }

        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);

        if (handler != null){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Param param = RequestHelper.createParam(req);
            Object result = null;
            Method actionMethod = handler.getControllerMethod();
            if (param == null || param.isEmpty()){
                result = ReflectionUtil.invokeMethod(controllerBean,actionMethod);
            }

            if (result instanceof View){
                handleViewResult((View) result,req,resp);
            }else if (result instanceof Data){
                handleDataResult((Data) result,resp);
            }
        }
    }

    /**
     * 跳转页面
     */
    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtils.isNotEmpty(path)) {
            if (path.startsWith("/")) { //重定向
                response.sendRedirect(request.getContextPath() + path);
            } else { //请求转发
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }

    /**
     * 返回JSON数据
     */
    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JSON.toJSON(model).toString();
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

}