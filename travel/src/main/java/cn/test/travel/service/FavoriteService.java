package cn.test.travel.service;

import cn.test.travel.domain.PageBean;
import cn.test.travel.domain.Route;

public interface FavoriteService {
    public Boolean isFavourite(String rid,int uid);

    void addFavorite(String rid, int uid);



}
