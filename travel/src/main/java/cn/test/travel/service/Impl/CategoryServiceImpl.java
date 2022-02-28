package cn.test.travel.service.Impl;

import cn.test.travel.dao.CategoryDao;
import cn.test.travel.dao.Impl.CategoryDaoImpl;
import cn.test.travel.domain.Category;
import cn.test.travel.service.CategoryService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        Jedis jedis=new Jedis();
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys= jedis.zrangeWithScores("category", 0, -1);
        List<Category> list=null;
        if(categorys==null || categorys.size()==0){
            System.out.println("从数据库查...");
            CategoryDao dao=new CategoryDaoImpl();
            list = dao.findAll();
            for (int i = 0; i <list.size() ; i++) {
                jedis.zadd("category", list.get(i).getCid(), list.get(i).getCname());
            }
        }else {
            System.out.println("从redis查...");
            list=new ArrayList<Category>();
            for (Tuple tuple: categorys) {
                Category category=new Category();
               category.setCname(tuple.getElement());
               category.setCid((int)tuple.getScore());
               list.add(category);
            }
        }

        return list;
    }
}
