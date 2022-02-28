package cn.test.travel.service;

import cn.test.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    Boolean regist(User user);

    Boolean active(String code);
    /**
     * 登录用户
     * @param user
     * @return
     */
    User login(User user);
}
