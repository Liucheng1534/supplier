package ruantong.supplier.Servser;

import ruantong.supplier.Bean.User;

/**
 * 此service主要是对用户的业务逻辑
 * @author Administrator
 *
 */
public interface UserService {
    //根据用户姓名查询用户信息
    public User select(String username);

    //注册用户信息
    public Integer insert(User user);
}
