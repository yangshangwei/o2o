package com.artisan.o2o.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.artisan.o2o.entity.PersonInfo;

public class ShopLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			PersonInfo user = (PersonInfo) userObj;
			if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1 && user.getUserType() == 1)
				return true;
		}
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/shopadmin/localauthlogin','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
