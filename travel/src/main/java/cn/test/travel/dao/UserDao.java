package cn.test.travel.dao;

import cn.test.travel.domain.User;

public interface UserDao {
    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    public User findUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    public void save(User user);

    /**
     * 通过激活码查找用户信息
     * @return
     */

    User findUserByCode(String code);
    /**
     * 修改激活码状态
     * @param user
     */
    void updateStatus(User user);


    User findUserByUsernameAndPassword(String username, String password);
}
