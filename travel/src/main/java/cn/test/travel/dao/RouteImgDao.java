package cn.test.travel.dao;

import cn.test.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    public List<RouteImg> findByRid(int rid);
}
