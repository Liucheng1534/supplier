package ruantong.supplier.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 此拦截器主要是对一些用户未登录下,可以访问的拦截
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest arg0,
								HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
						   Object arg2, ModelAndView arg3) throws Exception {

	}

	/**
	 * 登录拦截判断
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object arg2) throws Exception {

		HttpSession httpSession = request.getSession();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		if (httpSession != null) {

			String loginName = (String) httpSession.getAttribute("username");

			if (loginName == null || loginName.equals("")) {

				response.sendRedirect(basePath + "/electron/selectLimitFirst/1");

				return false;

			}

		} else {

			response.sendRedirect(basePath + "/electron/selectLimitFirst/1");

			return false;
		}

		return true;
	}

}
