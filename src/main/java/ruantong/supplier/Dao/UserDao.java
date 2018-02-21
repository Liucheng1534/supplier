package ruantong.supplier.Dao;

import ruantong.supplier.Bean.User;

/**
 * 此mapper主要是对用户表的操作
 * @author Administrator
 *
 */
public interface UserDao {

    //根据用户姓名查询用户信息
    public User select(String username);

    //注册用户信息
    public Integer insert(User user);

}
