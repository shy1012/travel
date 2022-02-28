package cn.test.travel.service.Impl;

import cn.test.travel.dao.FavoriteDao;
import cn.test.travel.dao.Impl.FavoriteDaoImpl;
import cn.test.travel.domain.Favorite;
import cn.test.travel.domain.PageBean;
import cn.test.travel.domain.Route;
import cn.test.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public Boolean isFavourite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite!= null;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

}
