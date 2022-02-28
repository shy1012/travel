package cn.test.travel.dao;

import cn.test.travel.domain.Route;
import javafx.beans.value.WritableDoubleValue;

import java.util.List;

public interface RouteDao {
    /**
     * 查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid,String rname);

    /**
     * 根据cid,start,pageSize查询当页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);
    /**
     * 根据id查询
     */
    public Route findOne(int rid);
}
