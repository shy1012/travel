package cn.test.travel.service.Impl;

import cn.test.travel.dao.UserDao;
import cn.test.travel.dao.Impl.UserDaoImpl;
import cn.test.travel.domain.User;
import cn.test.travel.service.UserService;
import cn.test.travel.util.MailUtils;
import cn.test.travel.util.UuidUtil;

public class UserServiceImpl  implements UserService {
    private UserDao dao=new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        User u = dao.findUserByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        String content="<a href='http://localhost/travel/user/active?code="+user
                .getCode()+"'>点击激活账号</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        dao.save(user);
        return true;
    }

    /**
     * 判断激活码
     * @param code
     * @return
     */
    public Boolean active(String code){
        User user= dao.findUserByCode(code);
        if(user!=null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
