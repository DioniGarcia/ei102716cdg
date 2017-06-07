package es.uji.ei102716cdg.controller.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.ChatService;

public class NotLoggedInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	ChatService chatService;
	
	@Autowired
	StudentDao studentDao;
	
	/**Comprueba que el usuario est� logueado 
	 * 
	 * Si no est� logueado redirige hacia la p�gina de LogIn
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("admin") != null) return true;
		
		if (session.getAttribute("user") == null){
			session.setAttribute("lastURL", request.getServletPath());
			response.sendRedirect(request.getContextPath() + "/login.html");
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("admin") != null) return;
		
		User user = (User) session.getAttribute("user");
		session.setAttribute("notifications", chatService.getNumberUnreadMessages(user.getNick()));
		session.setAttribute("myAvatarId", studentDao.getStudent(user.getNick()).getAvatar());
	}
}
