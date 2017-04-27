package es.uji.ei102716cdg.controller.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NotLoggedInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		return true;
		/*HttpSession session = request.getSession(true);
		
		if (session.getAttribute("user") == null){
			session.setAttribute("lastURL", request.getServletPath());
			response.sendRedirect(request.getContextPath() + "/login.html");
			return false;
		}
		return true;*/
	}
}
