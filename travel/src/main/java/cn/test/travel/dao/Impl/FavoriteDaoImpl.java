package cn.test.travel.dao.Impl;

import cn.test.travel.dao.FavoriteDao;
import cn.test.travel.domain.Favorite;
import cn.test.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite=null;
        try {
            String sql="select * from tab_favorite where rid= ? and uid= ?";
            favorite=template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        }catch (Exception e){

        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql="insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

}
