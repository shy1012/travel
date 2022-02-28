package cn.test.travel.service.Impl;

import cn.test.travel.dao.FavoriteDao;
import cn.test.travel.dao.Impl.FavoriteDaoImpl;
import cn.test.travel.dao.Impl.RouteDaoImpl;
import cn.test.travel.dao.Impl.RouteImgDaoImpl;
import cn.test.travel.dao.Impl.SellerDaoImpl;
import cn.test.travel.dao.RouteDao;
import cn.test.travel.dao.RouteImgDao;
import cn.test.travel.dao.SellerDao;
import cn.test.travel.domain.PageBean;
import cn.test.travel.domain.Route;
import cn.test.travel.domain.RouteImg;
import cn.test.travel.domain.Seller;
import cn.test.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao=new RouteDaoImpl();
    private RouteImgDao imgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pb=new PageBean<Route>();
        pb.setCurrentPage(currentPage);//设置当前页码
        pb.setPageSize(pageSize);//设置每页显示条数
        //设置总记录数
        int totalCount=routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //当页数据显示集合
        int start=(currentPage-1)*pageSize;//开始记录数
        List<Route> list=routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);
        //设置总页数
        int totalPage=totalCount%pageSize==0? totalCount/pageSize : (totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));
        List<RouteImg> imgList = imgDao.findByRid(route.getRid());
        route.setRouteImgList(imgList);
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        int count= favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }

}
