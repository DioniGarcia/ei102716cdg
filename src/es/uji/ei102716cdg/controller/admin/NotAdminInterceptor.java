package es.uji.ei102716cdg.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 */
public class NotAdminInterceptor extends HandlerInterceptorAdapter {

	/**Comprueba que el admin está logueado 
	 * 
	 * Si no está logueado redirige hacia la página de LogIn
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("admin") == null){
			session.setAttribute("lastURL", request.getServletPath());
			response.sendRedirect(request.getContextPath() + "/login.html");
			return false;
		}
		return true;
	}
}
