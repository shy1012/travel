package cn.test.travel.dao.Impl;

import cn.test.travel.dao.CategoryDao;
import cn.test.travel.domain.Category;
import cn.test.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
