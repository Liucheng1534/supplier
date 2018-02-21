package ruantong.supplier.Controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ruantong.supplier.Bean.User;
import ruantong.supplier.Servser.UserService;
import ruantong.supplier.Util.MD5Util;
import ruantong.supplier.Util.MySendMailThread;


@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@ResponseBody	
	@RequestMapping(value="/registUser",method=RequestMethod.POST)
	public String registUser(User user,HttpServletRequest request) {
		user.setRegistime(new SimpleDateFormat().format(new Date()));
		String password = MD5Util.MD5Encode(user.getPassword());
		user.setPassword(password);
		
		Integer judge = 0;
		
		try {
			
			judge = userService.insert(user);
			
		} catch (Exception e) {
			
			System.out.println("sql寮傚父!");
			
		}
		
		if (judge==0) {
			
			return "false";
			
		}else if (judge==1) {
			
			return "true";
			
		}else {
			
			return "true";
			
		}
		
	}

	@ResponseBody	
	@RequestMapping(value = "/UserLogin", method = RequestMethod.POST)
	public String userLogin(String username, String password,
			HttpServletRequest request) {
		
		
		if (username.isEmpty() || password.isEmpty()) {
			
			return "false";
			
		}
		
		User user = userService.select(username);
		String passwordMD5 = MD5Util.MD5Encode(password);
		
		if (user != null && user.getPassword().equals(passwordMD5)) {
			
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("uid", user.getUid());
			
			return "true";
			
		} else {
			
			return "false";
			
		}
		
	}
	
	/**
	 * 邮箱验证
	 * @param code(验证码)
	 * @param email(邮箱)
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "emailCode/{email}/{code}")
    public String emailCode(@PathVariable("code") String code,
    		@PathVariable("email") String email) {
		
    	User user = new User();
    	user.setEmail(email);
    	user.setUsername(code);;
    	
    	//发送验证信息
    	System.out.println(code+email);
    	new MySendMailThread(user).start();
    	
        return "true";
    }
	
	
}
