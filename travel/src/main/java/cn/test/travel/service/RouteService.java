package cn.test.travel.service;

import cn.test.travel.domain.PageBean;
import cn.test.travel.domain.Route;

/**
 * 根据类别分页查询
 */
public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

   public Route findOne(String rid);

}
