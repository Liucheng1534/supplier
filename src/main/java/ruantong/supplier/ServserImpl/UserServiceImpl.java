package ruantong.supplier.ServserImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruantong.supplier.Bean.User;
import ruantong.supplier.Dao.UserDao;
import ruantong.supplier.Servser.UserService;

/**
 * 此service主要是对用户的业务逻辑
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 根据用户姓名查询用户信息
	 */
	@Override
	public User select(String username) {

		return userDao.select(username);
	}

	/**
	 * 注册用户信息
	 */
	@Override
	public Integer insert(User user) {

		return userDao.insert(user);
	}

}
