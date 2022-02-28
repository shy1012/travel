package cn.test.travel.dao.Impl;

import cn.test.travel.dao.UserDao;
import cn.test.travel.domain.User;
import cn.test.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        User user=null;
        try {
            String sql="select * from tab_user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){

        }

        return user;
    }
    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void save(User user) {
        String sql="insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 通过激活码查找用户信息
     * @return
     */
    @Override
    public User findUserByCode(String code) {
        User user=null;
        try {
            String sql="select *from tab_user where code=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改激活码状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user=null;
        try {
            String sql="select * from tab_user where username=? and password=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
        }catch (Exception e){

        }
        return user;
    }
}
