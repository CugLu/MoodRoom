package mr.user.servlet.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mr.user.service.UserService;

import cn.itcast.servlet.BaseServlet;

public class AdminUserServlet extends BaseServlet {
	private UserService userService=new UserService();
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uid=request.getParameter("uid");
		userService.delete(uid);
		/*
		 * 竟可以这样返回！之前代码好像有几处也可以这样做
		 */
		return findAllUser(request, response);
	}
	public String findAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("activeUserList", userService.findActiveUser());
		request.setAttribute("unactiveUserList", userService.findUnactiveUser());
		return "f:/admin/users.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,String> errors=new HashMap<String,String>();
		
		if(username==null||username.trim().isEmpty())
			errors.put("username", "用户名不能为空");
		else if(!username.equals("Cailu"))
			errors.put("username", "用户名不对");
		
		if(password==null||password.trim().isEmpty())
			errors.put("password", "密码不能为空");
		else if(!password.equals("195823"))
			errors.put("password", "密码不对");
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			return "f:/admin/login.jsp";
		}
		
		request.getSession().setAttribute("session_adminUser", username);
		return "f:/admin/main.jsp";
	}
}
