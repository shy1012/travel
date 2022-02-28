package cn.test.travel.dao;

import cn.test.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
}
