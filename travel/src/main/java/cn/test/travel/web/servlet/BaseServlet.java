package cn.test.travel.web.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
     //   System.out.println(uri);
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
      //  System.out.println(methodName);//方法名称
        //System.out.println(this);//UserServlet的对象
            //获取方法
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接把传入的对象序列化成json，返回客户端
     * @param obj
     * @param response
     * @throws IOException
     */
    public  void writeValues(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 把传入的对象序列化成json，返回
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public  String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
