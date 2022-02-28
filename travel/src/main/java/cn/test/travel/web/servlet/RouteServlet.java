package cn.test.travel.web.servlet;

import cn.test.travel.domain.PageBean;
import cn.test.travel.domain.Route;
import cn.test.travel.domain.User;
import cn.test.travel.service.FavoriteService;
import cn.test.travel.service.Impl.FavoriteServiceImpl;
import cn.test.travel.service.Impl.RouteServiceImpl;
import cn.test.travel.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service=new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();
    public void pageQuery(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname=request.getParameter("rname");
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");
        int cid=0;//处理参数
        if(cidStr!=null && cidStr.length()>0 && !"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage=0;//当前页码，如果当前页码为0，则显示第一页
        if(currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }
        int pageSize=0;//每页显示条数，如果每页显示条数没有传参，则每页显示五条
        if(pageSizeStr!=null && pageSizeStr.length()>0){
           pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }

        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize,rname);
        writeValues(pb,response);
    }

    /**
     * 根据id查询线路信息
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void findOne(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String rid = request.getParameter("rid");
        Route route= service.findOne(rid);
        writeValues(route,response);
    }

    /**
     * 判断线路是否被用户收藏
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void isFavorite(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else {
            uid=user.getUid();
        }
        Boolean flag = favoriteService.isFavourite(rid, uid);
        writeValues(flag,response);
    }
    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void addFavorite(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
           return;
        }else {
            uid=user.getUid();
        }
        favoriteService.addFavorite(rid,uid);
    }


}