package cn.test.travel.dao;

import cn.test.travel.domain.Favorite;

import java.util.List;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid,int uid);

    public int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);

}
