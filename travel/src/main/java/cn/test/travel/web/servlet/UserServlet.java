package cn.test.travel.web.servlet;

import cn.test.travel.domain.ResultInfo;
import cn.test.travel.domain.User;
import cn.test.travel.service.Impl.UserServiceImpl;
import cn.test.travel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service=new UserServiceImpl();
    /**
     * 注册方法
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
     public void regist(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！！");

            ObjectMapper objectMapper=new ObjectMapper();
            String json = objectMapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
       // UserService service=new UserServiceImpl();
        Boolean flag= service.regist(user);
        ResultInfo info=new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败！！");
        }
      /*  ObjectMapper objectMapper=new ObjectMapper();
      String json = objectMapper.writeValueAsString(info);*/
         String json = writeValueAsString(info);
         response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
   public void login(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！！");
            ObjectMapper objectMapper=new ObjectMapper();
            String json = objectMapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
       // UserService service=new UserServiceImpl();
        User u= service.login(user);
        ResultInfo info=new ResultInfo();
        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名密码错误");
        }
        if(u!=null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("账户尚未激活，请前往注册邮箱登录激活");
        }
        if(u!=null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);
            info.setFlag(true);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void exit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index.html");
    }

    /**
     * 查询单个用户，返回用户名
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void findName(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        Object user = request.getSession().getAttribute("user");
        writeValues(user,response);
       /* ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),user);*/
    }
    /**
     *激活码激活
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void active(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String code = request.getParameter("code");
        if(code!=null){
         //   UserService service=new UserServiceImpl();
            Boolean flag= service.active(code);
            String msg=null;
            if(flag){
                msg="激活成功, 请<a href='login.html'>登录</a>";
            }else {
                msg="激活失败，请联系管理员！！！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}